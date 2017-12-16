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
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends AppCompatActivity implements IOpenGLDemo{

    static final float X=.525731112119133606f;
    static final float Z=.850650808352039932f;

    static float vertices[] = new float[]{
            -X, 0.0f, Z, X, 0.0f, Z, -X, 0.0f, -Z, X, 0.0f, -Z,
            0.0f, Z, X, 0.0f, Z, -X, 0.0f, -Z, X, 0.0f, -Z, -X,
            Z, X, 0.0f, -Z, X, 0.0f, Z, -X, 0.0f, -Z, -X, 0.0f
    };

    static short indices[] = new short[]{
            0,4,1, 0,9,4, 9,5,4, 4,5,8, 4,8,1,
            8,10,1, 8,3,10, 5,3,8, 5,2,3, 2,7,3,
            7,10,3, 7,6,10, 7,11,6, 11,0,6, 0,1,6,
            6,1,10, 9,0,11, 9,11,2, 9,2,5, 7,2,11 };

    float[] colors = {
            0f, 0f, 0f, 1f,
            0f, 0f, 1f, 1f,
            0f, 1f, 0f, 1f,
            0f, 1f, 1f, 1f,
            1f, 0f, 0f, 1f,
            1f, 0f, 1f, 1f,
            1f, 1f, 0f, 1f,
            1f, 1f, 1f, 1f,
            1f, 0f, 0f, 1f,
            0f, 1f, 0f, 1f,
            0f, 0f, 1f, 1f,
            1f, 0f, 1f, 1f

    };

    private GLSurfaceView view;
    private ByteBuffer vbb;
    private FloatBuffer vertex;
    private int index = 0;
    private FloatBuffer colorBuffer;
    private ShortBuffer shortBuffer;
    private float angle = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        view = new GLSurfaceView(this);
        view.setRenderer(new OpenGLRenderer(this));
        setContentView(view);
        vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertex = vbb.asFloatBuffer();
        vertex.put(vertices);
        vertex.flip();

        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        colorBuffer = cbb.asFloatBuffer();
        colorBuffer.put(colors);
        colorBuffer.flip();

        ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        shortBuffer = ibb.asShortBuffer();
        shortBuffer.put(indices);
        shortBuffer.flip();

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
        gl.glFrontFace(GL10.GL_CCW);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glCullFace(GL10.GL_BACK);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3 , GL10.GL_FLOAT , 0 , vertex);
        gl.glRotatef(angle , 0 , 1 , 0);

        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glColorPointer(4 , GL10.GL_FLOAT , 0  ,colorBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLES , indices.length , GL10.GL_UNSIGNED_SHORT , shortBuffer);

//        index++;
//        index %= 10;
//        switch (index){
//            case 0:
//            case 1:
//            case 2:
//                gl.glColor4f(1.f , 0.f , 0.f , 1.f);
//                gl.glDrawArrays(GL10.GL_TRIANGLES , 0  , vertex.limit());
//                break;
//            case 3:
//            case 4:
//            case 5:
//                gl.glColor4f(0.f , 1.f , 0.f ,1.f);
//                gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP , 0 , vertex.limit());
//                break;
//            case 6:
//            case 7:
//            case 8:
//            case 9:
//                gl.glColor4f(0.f,0.f,1.f , 1.f);
//                gl.glDrawArrays(GL10.GL_TRIANGLE_FAN , 0 , vertex.limit());
//                break;
//
//        }
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Log.i("tzh" , vertex.position() + "    "+ vertex.limit() + "    "+vertex.capacity());
//        gl.glDrawArrays(GL10.GL_POINTS , 0 ,vertex.limit()/3);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisable(GL10.GL_CULL_FACE);
        angle ++;
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
