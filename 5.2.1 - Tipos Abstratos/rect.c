#include "rect.h"
#include<stdio.h>
#include <stdlib.h>

typedef struct Rect
{
    int x;
    int y;
    int w;
    int h;
}Rect;

Rect* rect_new (void)
{
    Rect* this  = malloc(sizeof(Rect));
    this->x=10;
    this->y=10;
    this->w=20;
    this->h=20;
};

void rect_drag(Rect* this , int dx, int dy){
    this->x = this->x+dx;
    this->y = this->y+dy;
};
void rect_print(Rect* this){
    printf("Retangulo de altura:%d, e largura:%d. Coordenadas:(%d,%d) \n",this->h,this->w,this->x,this->y );
};
