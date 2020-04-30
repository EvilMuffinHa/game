package engine.io;

import org.lwjgl.glfw.GLFWVidMode;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

public class Display {
    private int width, height;
    private String windowName;
    private long window;
    public int frames;
    public int previousFrames = frames;
    public long time;
    public Input input;

    public Display (int width, int height, String windowName) {
        this.width = width;
        this.height = height;
        this.windowName = windowName;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public String getWindowName() {
        return windowName;
    }
    public long getWindow() {
        return window;
    }
    public int getFrames() {
        return frames;
    }
    public long getTime() {
        return time;
    }


    public void create() {
        if (!glfwInit()) {
            throw new IllegalStateException("Failed to initialize GLFW! ");
        }

        input = new Input();

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        window = glfwCreateWindow(this.width, this.height, this.windowName, 0, 0);
        if (window == 0) {
            throw new IllegalStateException("Failed to create window! ");
        }

        GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (videoMode.width() - this.width) / 2, (videoMode.height() - this.height ) / 2);
        glfwMakeContextCurrent(window);
        glfwSetKeyCallback(window, input.getKeyboardCallback());
        glfwSetMouseButtonCallback(window, input.getMouseButtonCallback());
        glfwSetCursorPosCallback(window, input.getMouseCallback());


        glfwShowWindow(window);
        glfwSwapInterval(1);

        time = System.currentTimeMillis();

    }

    public int update() {
        glfwPollEvents();
        frames++;
        if (System.currentTimeMillis() > time + 1000) {
            previousFrames = frames;
            time = System.currentTimeMillis();
            frames = 0;
            return frames;
        } else {
            return previousFrames;

        }
    }

    public void terminate() {
        Input.terminate();
        glfwWindowShouldClose(window);
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    public void swapBuffers() {
        glfwSwapBuffers(window);
    }

    public boolean shouldClose() {
        return glfwWindowShouldClose(window);
    }
}
