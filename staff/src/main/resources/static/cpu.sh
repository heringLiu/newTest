#!/usr/bin/env bash
get_cpu_time_splice_number()
{
    # 获取cpu各个时间片的数量
    cpu_time_splice_numbers_now=$(cat /proc/stat | grep 'cpu ' | awk '{print $2" "$3" "$4" "$5" "$6" "$7" "$8}')
    # 获取当前cpu空闲时间片
    eval ${1}=$(echo ${cpu_time_splice_numbers_now} | awk '{print $4}')
    # 获取当前总的cpu时间片
    eval ${2}=$(echo ${cpu_time_splice_numbers_now} | awk '{print $1+$2+$3+$4+$5+$6+$7}')
}

get_cpu_rate()
{
    # 声明相隔1秒的cpu空闲时间片和总时间片变量
    declare idle_cpu_time_splice_now idle_cpu_time_splice_end total_cpu_time_splice_now total_cpu_time_splice_end
    get_cpu_time_splice_number idle_cpu_time_splice_now total_cpu_time_splice_now
    sleep 1
    get_cpu_time_splice_number idle_cpu_time_splice_end total_cpu_time_splice_end
    # 获取cpu空闲时间差
    cpu_idle_number=`expr ${idle_cpu_time_splice_end} - ${idle_cpu_time_splice_now}`
    # 获取cpu总时间片差
    cpu_total_number=`expr ${total_cpu_time_splice_end} - ${total_cpu_time_splice_now}`
    cpu_rate=`awk 'BEGIN{printf "%.2f", 100 - '${cpu_idle_number}' / '${cpu_total_number}' * 100}'`
    echo ${cpu_rate}
}

get_memory_rate()
{
    # 获取系统物理内存大小，单位KB
    totalMemSize=$(awk '/MemTotal:/ {print $2}' /proc/meminfo)
    # 获取系统可用内存大小，单位KB
    freeMemSize=$(awk '/MemFree:/ {print $2}' /proc/meminfo)
    # 获取buffer大小，单位KB
    bufferMemSize=$(awk '/Buffers:/ {print $2}' /proc/meminfo)
    # 获取cache大小，单位KB
    cacheMemSize=$(awk '/^Cached:/ {print $2}' /proc/meminfo)
    # 使用的内存大小
    usedMemSize=`expr ${totalMemSize} - ${freeMemSize} - ${bufferMemSize} - ${cacheMemSize}`
    # 内存使用率
    memPercent=`awk 'BEGIN{printf "%.2f",'${usedMemSize}'/'${totalMemSize}' * 100}'`
    echo ${memPercent}
}

get_disk_rate() {
    # 获取磁盘总量
    totalDiskSize=$(df |grep -w "/" | awk '{if(NR == 1)print $2}')
    # 获取磁盘使用量
    usedDiskSize=$(df |grep -w "/" | awk '{if(NR == 1)print $3}')
    # 磁盘使用率
    diskPercent=`awk 'BEGIN{printf "%.2f",'${usedDiskSize}'/'${totalDiskSize}' * 100}'`
    echo ${diskPercent}
}

main()
{
    #输出cpu,内存，硬盘的使用率
    echo "cpu使用率：" $(get_cpu_rate)
    echo "内存使用率：" $(get_memory_rate)
    echo "磁盘使用率：" $(get_disk_rate)
}
main

