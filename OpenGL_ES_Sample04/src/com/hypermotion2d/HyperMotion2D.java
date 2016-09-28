/********************************************
** GLSurfaceView.Rendererクラスを実装したクラスで
** ２Dゲーム開発の中心となるクラスです。
** ゲームを作るのに書き足していくファイルで、ほとんどこの
** ファイルでコーディングしたらゲームが作れます。
** このプロジェクトではpng画像ファイルを表示させ移動も実装
** します。
** OpenGLでは２Dも３Dもテクスチャ画像として取り扱います。
** 使用するパラメータについて
   _texX : テクスチャのX座標
   _texY : テクスチャのY座標
   _texWidth  : テクスチャの幅
   _texHeight : テクスチャの高さ（マイナス）
   _pos._x    : 画像を配置するX座標
   _pos._y    : 画像を配置するY座標
   _pos._z    : 画像を配置するZ座標
   _width     : 画像を配置するときの画像の幅
   _height    : 画像を配置するときの画像の高さ
   
   実装内容：複数のpng画像を表示し、タッチした画像のみ
           拡大します。
********************************************/

package com.hypermotion2d;

import android.content.Context;
import android.opengl.GLSurfaceView;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import com.roxiga.hypermotion2d.*;


public class HyperMotion2D implements GLSurfaceView.Renderer
{
        private Context _context;
        //画面の幅
        public int _width;
        //画面の高さ
        public int _height;
        //タッチ動作の有無
        public boolean _touch;
        
        //2次元スプライト
        private Sprite2D[] _img = new Sprite2D[3];

        //@Override
        //コンストラクタ
        public HyperMotion2D(Context context)
        {
            _context = context;
        }

        //@Override
        //描画するごとに毎フレーム呼び出される関数
        public void onDrawFrame(GL10 gl)
        {
            //描画用バッファをクリア
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT
                       | GL10.GL_DEPTH_BUFFER_BIT);
            
            //画像の描画
            for (int i = 0; i < _img.length; i++){
                _img[i].draw(gl);
            }
        }

        //@Override
        //サーフェイスのサイズ変更時に呼ばれる関数
        public void onSurfaceChanged(
			GL10 gl, int width, int height)
        {
        }

        //@Override
        //サーフェイスが生成される際、または再生成される際に呼ばれる関数
        public void onSurfaceCreated(
            GL10 gl, EGLConfig config)
        {
            //背景色
            gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
            //ディザを無効化
            gl.glDisable(GL10.GL_DITHER);
            //深度テストを有効化
            gl.glEnable(GL10.GL_DEPTH_TEST);
            //テクスチャ機能ON
            gl.glEnable(GL10.GL_TEXTURE_2D);
            //透明可能に
            gl.glEnable(GL10.GL_ALPHA_TEST);
            //ブレンド可能に
            gl.glEnable(GL10.GL_BLEND);
            //色のブレンド方法
            gl.glBlendFunc(GL10.GL_SRC_ALPHA,
                           GL10.GL_ONE_MINUS_SRC_ALPHA);
            //テクスチャの読み込み
            for (int i = 0; i < _img.length; i++){
                _img[i] = new Sprite2D();
                _img[i].setTexture(gl, _context.getResources(), R.drawable.img);
                _img[i]._pos._x = 200;
                _img[i]._pos._y = 256 * i;
            }
        }

        //HyperMotion2DActivity.javaのonTouchEventで
        //画面タッチされ押されたときに呼ばれる関数
        public void actionDown(float x, float y)
        {
            _touch = true;
        }

        //HyperMotion2DActivity.javaのonTouchEventで
        //画面さがドラッグされ押されたときに呼ばれる関数
        public void actionMove(float x, float y)
        {
        }

        //HyperMotion2DActivity.javaのonTouchEventで
        //画面タッチしていたのが離さされたときに呼ばれる関数
        public void actionUp(float x, float y)
        {
            for (int i = 0; i < _img.length; i++){
                if (_img[i].hit(x, _height - y)){
                    _img[i]._pos._x -= 5;
                    _img[i]._pos._y -= 5;
                    _img[i]._width += 10;
                    _img[i]._height += 10;
                    break;
                }
            }
        }
    }
