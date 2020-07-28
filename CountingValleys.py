#!/usr/bin/python

import math
import os
import random
import re
import sys

def placementFunction(movement, placement):
    if movement == 'U':
        return placement+1
    return placement-1

def valleyFunction(tup,placement):
    new_placement = placementFunction(tup[1],placement)
    prior_low_level = placement<0
    sealevel = new_placement==0

    if sealevel and prior_low_level:
        return 1
    return 0

def countingValleys(n,s):
    valleys = 0
    plment = placementFunction(s[0],0)
    for i in range(1,n):
        tup = (s[i-1],s[i])
        valleys += valleyFunction(tup,plment)
        plment = placementFunction(s[i],plment)
    return valleys

def main():
    problem_file = open('input.txt','r')
    length_string = problem_file.read().split('\n')
    print(length_string)
    size = int(length_string[0])
    arr_string = length_string[1]
    print(str(countingValleys(size,arr_string)))




main()