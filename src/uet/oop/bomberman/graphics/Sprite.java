package uet.oop.bomberman.graphics;

import javafx.scene.image.*;

/**
 * Lưu trữ thông tin các pixel của 1 sprite (hình ảnh game)
 */
public class Sprite {

    public static final int DEFAULT_SIZE = 40;
    public static final int SCALED_SIZE = DEFAULT_SIZE;
    private static final int TRANSPARENT_COLOR = 0xffff00ff;
    private int _x, _y;
    private int posX, posY;
    public int[] _pixels;
    protected int _realWidth;
    protected int _realHeight;
    private SpriteSheet _sheet;

    public static Sprite[][] player_1 = new Sprite[4][6];
    public static Sprite[][] player_2 = new Sprite[4][6];
    public static Sprite[][] boom = new Sprite[1][3];
    public static Sprite[][] botplayer = new Sprite[6][3];
    public static Sprite[][] crep1 = new Sprite[1][4];
    public static Sprite[][] crep2 = new Sprite[1][4];
    public static Sprite[][] boss = new Sprite[1][4];
    public static Sprite[][] ghost = new Sprite[1][3];
    public static Sprite[][] flame = new Sprite[9][3];
    public static Sprite[][] items = new Sprite[4][3];
    public static Sprite[][] grass = new Sprite[1][7];
    public static Sprite[][] wall = new Sprite[1][8];
    public static Sprite[][] explosionBomb = new Sprite[9][3];
    public static Sprite[][] diePlayer = new Sprite[6][3];
    public static Sprite[][] bomSao   = new Sprite[1][3];
    public static Sprite[][] no = new Sprite[1][2];

    //khoi tao toan bo cac sprite chua hinh anh cua cac doi tuong, mang sap xep nhu tren anh
    static {
        set_moveset(player_1, SpriteSheet.all, 6, 4, 45, 60, 0, 0);
        set_moveset(player_2, SpriteSheet.all, 6, 4, 50, 57, 0, 240);
        set_moveset(boom, SpriteSheet.all, 3, 1, 44, 44, 0, 900);
        set_moveset(botplayer, SpriteSheet.all, 3, 6, 67, 67, 200, 500);
        set_moveset(crep1, SpriteSheet.all,4 , 1, 40, 44, 900, 800);
        set_moveset(crep2, SpriteSheet.all,4 , 1, 45, 58, 900, 700);
        set_moveset(boss, SpriteSheet.all,4 , 1, 135, 173, 900, 500);
        set_moveset(ghost, SpriteSheet.all,3 , 1, 45, 67, 900, 900);
        set_moveset(flame, SpriteSheet.all,3 , 9, 40, 40, 0, 500);
        set_moveset(items, SpriteSheet.all,3 , 4, 42, 45, 500, 500);
        set_moveset(grass, SpriteSheet.all,7 , 1, 40, 40, 0, 1000);
        set_moveset(wall, SpriteSheet.all, 8, 1, 40,67, 400, 998);
        set_moveset(explosionBomb, SpriteSheet.all, 3, 9, 40, 40, 0, 500);
        set_moveset(diePlayer, SpriteSheet.all, 3, 6, 67, 67, 200, 500);
        set_moveset(bomSao, SpriteSheet.all,3, 1, 40, 40, 450, 750);
        set_moveset(no, SpriteSheet.all, 2, 1, 50, 50, 600, 750);
    }

    public static void set_moveset(Sprite[][] x, SpriteSheet a, int length, int height, int rw, int rh, int posX, int posY) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                x[i][j] = new Sprite(j, i, a, rw, rh, posX, posY);
            }
        }
    }

    /*
    |--------------------------------------------------------------------------
    | Board sprites
    |--------------------------------------------------------------------------
     */

    public Sprite(int x, int y, SpriteSheet sheet, int rw, int rh, int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        _pixels = new int[rw * rh];
        _x = x * rw;
        _y = y * rh;
        _sheet = sheet;
        _realWidth = rw;
        _realHeight = rh;
        load();
    }

    public Sprite(int size, int color) {
        _pixels = new int[_realWidth * _realHeight];
        setColor(color);
    }

    private void setColor(int color) {
        for (int i = 0; i < _pixels.length; i++) {
            _pixels[i] = color;
        }
    }


    private void load() {
        for (int y = 0; y < _realHeight; y++) {
            for (int x = 0; x < _realWidth; x++) {
                _pixels[x + y * _realWidth] = _sheet._pixels[(x + _x + posX) + (y + _y + posY) * _sheet.width];
            }
        }
    }

    public static Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2, int animate, int time) {
        int calc = animate % time;
        int diff = time / 3;

        if (calc < diff) {
            return normal;
        }

        if (calc < diff * 2) {
            return x1;
        }
        return x2;
    }

    public static Sprite movingSprite(Sprite x1, Sprite x2, int animate, int time) {
        int diff = time / 2;
        return (animate % time > diff) ? x1 : x2;
    }

    public int getSize() {
        return _realWidth*_realHeight;
    }

    public int getPixel(int i) {
        return _pixels[i];
    }


    public Image getFxImage() {
        WritableImage wr = new WritableImage(_realWidth, _realHeight);
        PixelWriter pw = wr.getPixelWriter();
        for (int x = 0; x < _realWidth; x++) {
            for (int y = 0; y < _realHeight; y++) {
                if (_pixels[x + y * _realWidth] == TRANSPARENT_COLOR) {
                    pw.setArgb(x, y, 0);
                } else {
                    pw.setArgb(x, y, _pixels[x + y * _realWidth]);
                }
            }
        }
        Image input = new ImageView(wr).getImage();
        return input;
        //return resample(input, SCALED_SIZE / DEFAULT_SIZE);
    }

    private Image resample(Image input, int scaleFactor) {
        final int W = (int) input.getWidth();
        final int H = (int) input.getHeight();
        final int S = scaleFactor;

        WritableImage output = new WritableImage(
                W * S,
                H * S
        );

        PixelReader reader = input.getPixelReader();
        PixelWriter writer = output.getPixelWriter();

        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                final int argb = reader.getArgb(x, y);
                for (int dy = 0; dy < S; dy++) {
                    for (int dx = 0; dx < S; dx++) {
                        writer.setArgb(x * S + dx, y * S + dy, argb);
                    }
                }
            }
        }
        return output;
    }
}
