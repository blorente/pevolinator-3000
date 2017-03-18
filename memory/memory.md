PEVOLINATOR - 3000
===============

Aplicación
---------

![Vista de la aplicacion al iniciarla](./graphics_captures/App_explanation.png)
### Instrucciones de uso (Hints):
Hemos añadido los valores predefinidos para cada uno de los campos de la interfaz mediante un sistema de Hints. Estos campos a la vez guardan el último valor introducido pero lo ocultan cuando hacemos click para cambiar dicho valor pero sin perderlo si dejamos de escribir en un campo sin haber introducido ningún valor este recuperará su ultimo valor.

### Elitismo
Uno de las diferencias en nuestra aplicación con respecto a la que se nos mostró en clase es el funcionamiento del elitismo. Pensamos que sería mas interesante poder elegir el porcentaje del elitismo mas que simplemente poder decir si se quiere elitismo o no. En caso de no querer elitismo simplemente hay que poner 0 para que el porcentaje de la elite guardado en cada iteración sea 0.

### Funcionamiento de la seed
Nuestro programa tiene la posibilidad tanto de usar una seed para crear la población inicial como de que dicha seed sea un número aleatorio. Para ello en la interfaz hemos añadido un campo Seed el cual si recibe el valor cero usara una seed aleatoria y si recibe cualquier otro valor utilizará dicho valor como seed. En ambos casos la seed se utiliza solo para la población inicial que para la misma seed siempre será la misma.



### Estructura

- Representación de individuos.
- Arquitectura de funciones de fitness.
- Localización de algoritmos de cruce, selección.
- Implementación de elitismo.

### Métodos y algoritmos utilizados

- Código de seleccionadores
- Código del cruce
- Código de la mutación
- (?) Codigo de las fitness

## Fotos de las gráficas

First function:    
![Función 1](./graphics_captures/first_function.png)

Second function:    
![Función 1](./graphics_captures/second_function.png)

Third function:    
![Función 1](./graphics_captures/third_function.png)

Fourth function for n=1:    
![Función 1](./graphics_captures/fourth_function_n_1.png)

Fourth function for n=3:    
![Función 1](./graphics_captures/fourth_function_n_3.png)

Fourth function for n=7:    
![Función 1](./graphics_captures/fourth_function_n_7.png)

Fifth function:    
![Función 1](./graphics_captures/fifth_function.png)
