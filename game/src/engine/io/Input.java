package engine.io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class Input {
    private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
    private static boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];

    private static double mouseX, mouseY;

    private static GLFWKeyCallback keyboard;
    private static GLFWCursorPosCallback mouse;
    private static GLFWMouseButtonCallback mouseButton;

    public GLFWKeyCallback getKeyboardCallback() {
        return keyboard;
    }

    public GLFWCursorPosCallback getMouseCallback() {
        return mouse;
    }

    public GLFWMouseButtonCallback getMouseButtonCallback () {
        return mouseButton;
    }

    public static void terminate() {
        keyboard.free();
        mouse.free();
        mouseButton.free();
    }

    public static boolean isKeyDown(int key) {
        return keys[key];
    }

    public static boolean isButtonDOwn(int button) {
        return buttons[button];
    }


    public static double getMouseX() {
        return mouseX;
    }

    public static double getMouseY() {
        return mouseY;
    }

    public Input() {
        keyboard = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                keys[key] = (action != GLFW.GLFW_RELEASE);

            }
        };
        mouse = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                mouseX = xpos;
                mouseY = ypos;
            }
        };
        mouseButton = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                buttons[button] = (action != GLFW.GLFW_RELEASE);
            }
        };
    }

}
