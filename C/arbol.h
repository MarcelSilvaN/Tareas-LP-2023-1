#include <stdlib.h>
#include <stdio.h>
#include <string.h>

typedef struct Nodo{ 
    struct Nodo* padre;
    char tipo[64];
    void* contenido;
}Nodo;

typedef struct Lista{
    int largo_actual;
    int largo_maximo;
    Nodo* arreglo;
} Lista;

typedef struct Directorio{
    char nombre[128];
    Lista* hijos;
} Directorio;

typedef struct Archivo{
    char nombre[128];
    char contenido[256];
} Archivo;


Lista* crear_lista(int largo_maximo_inicial);
void insertar_lista(Lista* lista, Nodo* nodo);
Nodo* buscar_directorio(Directorio* actual, char* nombre);
Nodo* buscar_archivo(Directorio* actual, char* nombre);
Nodo* crear_nodo(Nodo* padre, char* tipo, char* nombre);
void mkdir(Nodo* actual, char * nombre_directorio);
void touch(Nodo* actual, char* nombre_archivo);
void write(Nodo* actual, char* nombre_archivo , char* contenido);
void cat(Nodo* actual, char* nombre_archivo);
void ls(Nodo* actual);
void ls_dir(Nodo* actual, char* nombre_directorio);
void mapdir(Nodo* actual , void(*instruccion)(Nodo*, char*) , char* parametro_instruccion);