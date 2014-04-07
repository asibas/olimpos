package com.me.pruebaFiguras;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;

public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //se quita la limitacion en la que las imagenes tienen que tener altura y anchura potencia de dos
        Texture.setEnforcePotImages(false);
        
        //Configuracion de la aplicacion
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        //Creo que queda configurado para usar openGL2
        cfg.useGL20 = false;
        
        //Iniciamos la clase que extiende de game
        initialize(new PruebaFiguras(), cfg);
    }
}