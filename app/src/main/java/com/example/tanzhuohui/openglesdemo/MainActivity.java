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
            -0.8f, -0.4f * 1.732f, 0.0f,
            -0.4f, 0.4f * 1.732f, 0.0f,
            0.0f, -0.4f * 1.732f, 0.0f,
            0.4f, 0.4f * 1.732f, 0.0f,
    };

    private GLSurfaceView view;
    private ByteBuffer vbb;
    private FloatBuffer vertex;
    private int index = 0;

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
        vertex.put(vertexArray);
        vertex.flip();
//        vertex.position(0);
    }

    @Override
    public void drawScene(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT
                | GL10.GL_DEPTH_BUFFER_BIT);
//        gl.glColor4f(1.0f ,0.f , 0.f , 0.5f);
//        gl.glPointSize(18f);
        gl.glLoadIdentity();
        gl.glTranslatef(0 , 0 ,-4);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3 , GL10.GL_FLOAT , 0 , vertex);

        index++;
        index %= 10;
        switch (index){
            case 0:
            case 1:
            case 2:
                gl.glColor4f(1.f , 0.f , 0.f , 1.f);
                gl.glDrawArrays(GL10.GL_LINES , 0  , vertex.limit()/3);
                break;
            case 3:
            case 4:
            case 5:
                gl.glColor4f(0.f , 1.f , 0.f ,1.f);
                gl.glDrawArrays(GL10.GL_LINE_STRIP , 0 , vertex.limit()/3);
                break;
            case 6:
            case 7:
            case 8:
            case 9:
                gl.glColor4f(0.f,0.f,1.f , 1.f);
                gl.glDrawArrays(GL10.GL_LINE_LOOP , 0 , vertex.limit()/3);
                break;

        }
        Log.i("tzh" , vertex.position() + "    "+ vertex.limit() + "    "+vertex.capacity());
//        gl.glDrawArrays(GL10.GL_POINTS , 0 ,vertex.limit()/3);
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
