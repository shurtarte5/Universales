# Universales
Proyecto demo para seguros universales


El objetivo principal fue consumir el api The movie DB
Para este consumo se utilizaron RETROFIT
    implementation 'com.squareup.retrofit2:retrofit:2.1.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.1.0'
    implementation 'com.google.code.gson:gson:2.8.6'
    
    
Para el manejo de datos locales se utilizo componentes de arquitectura de android
ROOM: para base de datos local

    implementation "androidx.lifecycle:lifecycle-extensions:$lifecycle_version"
    annotationProcessor "androidx.lifecycle:lifecycle-compiler:$lifecycle_version"

    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version"



Para el despliegue de informacion se utilizaron componentes de MATERIAL DESIGN

      implementation 'com.android.support:recyclerview-v7:29.0.0'
      implementation 'com.android.support:cardview-v7:29.0.0'
      
      
En cuanto al uso de layouts se utilizarons la solicitada por el test CONSTRAINT Layout , 
ademas de Relative layout, coordinator layout

      
      

      
Para la carga de imagenes de la api se utilizo glide
    implementation 'com.github.bumptech.glide:glide:4.10.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.10.0'
    
    
La version minima de sdk para ejecutar el proyecto es 23
    






