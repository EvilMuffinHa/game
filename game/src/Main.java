import engine.io.Display;
import engine.io.Input;
import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.glfwSetWindowTitle;


public class Main{

    public final static int WIDTH = 640, HEIGHT = 480;
    public final String windowName = "Game!";
    public Display display;

    public void run() {
        init();
        while (!display.shouldClose()) {
            update();
            render();
            if (Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
                return;
            }
        }
        display.terminate();

    }
    public void init() {
        System.out.println("Initializing Game! ");
        display = new Display(WIDTH, HEIGHT, windowName);
        display.create();

    }
    private void update() {
        System.out.println("Updating Game! ");
        int frames = display.update();
        glfwSetWindowTitle(display.getWindow(), display.getWindowName() + " (Frames : " + frames + ")");

    }

    private void render() {
        System.out.println("Rendering Game! ");
        display.swapBuffers();

    }

    public static void main(String[] args) {
        new Main().run();
    }
}
