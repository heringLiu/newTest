package com.management.staff.demo.config.security.service;

import com.management.staff.demo.entity.Permission;
import com.management.staff.demo.entity.Users;
import com.management.staff.demo.service.PermissionService;
import com.management.staff.demo.service.UsersService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Resource
    private UsersService usersService;

    @Resource
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersService.queryByUserId(username);
        if (users == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        // 查询用户权限
        List<Permission> permissionList = permissionService.findPermissionListByUserId(users.getId());
        List<String> codeList = permissionList.stream().filter(Objects::nonNull)
                .map(Permission::getCode)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        String[] strings = codeList.toArray(new String[codeList.size()]);
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(strings);
        users.setPermissionList(permissionList);
        users.setGetAuthorities(authorityList);
        return users;
    }
}
