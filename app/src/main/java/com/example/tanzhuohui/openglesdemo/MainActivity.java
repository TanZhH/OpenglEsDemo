package com.example.tanzhuohui.openglesdemo;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends AppCompatActivity implements IOpenGLDemo{

    private float[] vertexArray = new float[]{
            -0.8f , -0.4f * 1.732f , 0.0f ,
            0.8f , -0.4f * 1.732f , 0.0f ,
            0.0f , 0.4f * 1.732f , 0.0f ,
    };

    private GLSurfaceView view;
    private ByteBuffer vbb;
    private FloatBuffer vertex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        view = new GLSurfaceView(this);
        view.setRenderer(new OpenGLRenderer(this));
        setContentView(view);
        vbb = ByteBuffer.allocateDirect(vertexArray.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertex = vbb.asFloatBuffer();
    }

    @Override
    public void drawScene(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT
                | GL10.GL_DEPTH_BUFFER_BIT);
        Log.i("tzh" , vertexArray[0]+"   "+vertexArray[1]+"   "+vertexArray[2]+"   ");
        vertex.clear();
        vertex.put(vertexArray);
        vertex.position(0);
        gl.glColor4f(1.0f ,0.f , 0.f , 0.5f);
        gl.glPointSize(8f);
        gl.glLoadIdentity();
        gl.glTranslatef(0 , 0 ,-4);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3 , GL10.GL_FLOAT , 0 , vertex);
        gl.glDrawArrays(GL10.GL_POINTS , 0  ,3);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        view.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        view.onPause();
    }
}
