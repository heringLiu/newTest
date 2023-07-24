#!/bin/bash

keywords=("a" "b")

for var in ${keywords[@]}
do
        echo $var
        cat ../corpus.txt | grep $var | wc -l        
        cat ../corpus.txt | grep $var > search_res/$var.txt
done