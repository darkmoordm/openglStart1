package openglstart;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;


import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.system.MemoryUtil.*;


public class WindowStart {

    private long window;
    int width = 800, height = 600;

    public void run() {
        init();
        loop();

        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        glfwTerminate();// очистка выделенных ресурсов
        glfwSetErrorCallback(null).free();

    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).free();

        if (!glfwInit()) {// включили GLFW
            throw new IllegalStateException("не инициализирован GLFW");
        }
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(width, height, "Opengl_Start", 0, 0);
        if (window == NULL) {
            throw new RuntimeException("ошибка создания окна lwjgl");// создали окно
        }
         // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE){
            glfwSetWindowShouldClose(window, true);
            }
        });
        
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());//разрешение главного монитора
        
        
        
        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);// вертильная синхронизация
        glfwShowWindow(window);
    }

    private void loop() {
        GL.createCapabilities();
        glClearColor(0.9f, 0.6f, 0.0f, 1.0f);
        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);// clear the framebuffer
            glfwSwapBuffers(window);//Функция glfwSwapBuffers заменяет цветовой буфер (большой буфер, содержащий значения цвета для каждого пикселя в GLFW окне), который использовался для отрисовки во время текущей итерации и показывает результат на экране.
            glfwPollEvents();// проверяет были ли вызваны какие либо события (вроде ввода с клавиатуры или перемещение мыши) 

        }
    }

}

