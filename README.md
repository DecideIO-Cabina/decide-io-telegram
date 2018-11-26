# decide-io-telegram
Bot de Telegram para votar en [DecideIO](https://github.com/EGCETSII/decide)

## Cómo trabajar en el proyecto
El bot se implementará en Java y utiliza Maven para la gestión de dependencias, por lo que cualquier IDE que soporte estas tecnologías es suficiente. Nosotros utilizamos Eclipse 2018‑09. Sólo es necesario clonar el proyecto y ejecutarlo como aplicación de Spring Boot desde CabinaTelegramApplication.java

## Gestión de cambios
Para desarrollar la aplicación, utilizaremos un sistema de ramas para organizar los commits al repositorio. Se ruega a todos los desarrolladores que sigan este sistema para evitar confusiones y situaciones difíciles, especialmente en las fechas cercanas a los milestones.

Habrá dos ramas permanentes, master (producción) y develop (desarrollo). La rama master será la que se despliegue automáticamente al recibir cambios, y representa la versión actual de la aplicación. La rama develop contendrá los cambios pendientes de pasar a master.

#### Cómo desarrollar una funcionalidad nueva:
1. Crear una rama desde develop que se llame feat-XXXXXX (por ejemplo, feat-restclient)
2. Desarrollar los cambios, haciendo commits en nuestra rama. Se recomienda hacer commits frecuentes, aunque la funcionalidad no esté terminada.
3. Traer los cambios que hayan ocurrido en develop a nuestra rama. Comprobar que la aplicación funciona correctamente.
4. Hacer merge de nuestra rama a develop.

#### Para corregir bugs en master:
Los errores que se encuentren en el código de master deben ser corregidos lo antes posible. Por tanto, el procedimiento para arreglar bugs en producción será:
1. Crear una rama desde master que se llame hotfix-XXXXXX (por ejemplo, hotfix-nullpointer-votacion)
2. Arreglar el error, commiteando a nuestra rama.
3. Traer los cambios que hayan ocurrido en master a nuestra rama. Comprobar que la aplicación funciona correctamente.
4. Hacer merge de nuestra rama a master

Si un error requiere grandes cambios (a partir de un par de horas de trabajo), se utilizará el procedimiento establecido para funcionalidades.

#### Para pasar los cambios de develop a master:
Cuando se considere que los cambios en develop merecen ser desplegados como una nueva versión de la aplicación (por ejemplo, se termina una funcionalidad de forma que es completamente usable), se creará una Pull Request. El administrador del repositorio la revisará y, si da el visto bueno, aceptará los cambios en master.

![gráfico gitflow.png](https://raw.githubusercontent.com/DecideIO-Cabina/decide-io-telegram/master/doc/img/gitflow.png)

## Gestión de incidencias
Se utilizarán issues de github tanto para los bugs que se encuentren como para definir nuevas funcionalidades a implementar. Es importante seguir las plantillas establecidas para no olvidar información importante.

Es muy importante que se creen issues siempre que aparezca una idea o bug, aunque su autor piense trabajar en ella directamente (para eso puede asignarse la issue a sí mismo). Así todos los desarrolladores pueden comprobar fácilmente el estado del proyecto y encontrar tareas en las que trabajar.

Cuando se trabaje en una issue, los commits deberán hacer referencia a la misma mediante el código de issue. Por ejemplo "Implementado cliente REST #7" hace referencia a la issue #7, y "Arreglado bug en la votación fixes #14" cerrará la issue al hacer commit ([Closing issues using keywords](https://help.github.com/articles/closing-issues-using-keywords/)). Esto ayuda a los demás desarrolladores a ver el progreso de la issue.

