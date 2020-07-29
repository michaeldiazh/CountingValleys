#include <assert.h>
#include <limits.h>
#include <math.h>
#include <stdbool.h>
#include <stddef.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


static int placementFunction(char movement, int placement){
    if(movement == 'U'){
        return placement+1;
    }

    return placement-1;
}
static int valleyFunction(char* tuple,int placement){
    int old_placement = *(&placement);
    placement = placementFunction(tuple[1],placement);
    bool prior_low_level = (old_placement < 0);
    bool sealevel = (placement == 0);

    if(sealevel && prior_low_level){
        return 1;
    }
    return 0;
}

int countValleys(int n, char * s){
    int valleys = 0;
    int plment = placementFunction(s[0],0);
    char * tup = (char*)malloc(2);
    for (int i  = 1; i < n; i++){
        tup[0] = s[i-1]; tup[1] = s[i];
        valleys += valleyFunction(tup, plment);
        plment = placementFunction(s[i],plment);
    }
    return valleys;
}

int main(){
    // Create a file pointer
    FILE * file_pointer;
    // Let create a str pointer
    char * filename = "input.txt";
    char * str = (char*)malloc(100);
    // Open the file and store its address fopen()
    file_pointer = fopen(filename,"r");

    // First Read() -> n, Sec. Read() -> s
    fgets(str,100,file_pointer);
    
    int n = atoi(str);
   
    fgets(str,100,file_pointer);
    char * s = *(&str);

    printf("%d\n",countValleys(n,s));

    return 0;
}