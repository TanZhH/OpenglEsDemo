package com.example.tanzhuohui.openglesdemo;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


/**
 * Created by tanzhuohui on 2017/12/15.
 *
 */

public class OpenGLRenderer implements GLSurfaceView.Renderer {

    private IOpenGLDemo openGLDemo;

    public OpenGLRenderer(IOpenGLDemo openGLDemo) {
        this.openGLDemo = openGLDemo;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.f , 0.f , 0.f , 0.5f);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT
                | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glClearDepthf(1.f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT , GL10.GL_NICEST);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0 , 0 ,width , height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl , 45.f , (float)width/(float)height , 0.1f , 100.f);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        if(openGLDemo != null) {
            openGLDemo.drawScene(gl);
            Log.d("tzh" , "test");
        }
    }
}
