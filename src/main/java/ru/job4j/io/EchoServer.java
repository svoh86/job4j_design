package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Класс описывает взаимодействие между клиентом и сервером с помощью сокетов.
 * Добавлен логгер
 *
 * @author Svistunov Mikhail
 * @version 1.1
 */
public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    /**
     * Метод создает сервер, с указание порта 9000.
     * Чтобы клиент мог узнать, где находится сервер ему нужен адрес и порт, 9000 - это порт.
     * По умолчанию адрес будет localhost.
     * Вызов метода accept() заставляет программу ждать подключений по указанному порту,
     * работа программы продолжится только после подключения клиента.
     * После успешного подключения метод возвращает объект Socket,
     * который используется для взаимодействия с клиентом.
     * Метод accept принимает один запрос от клиента, чтобы отправить второй,
     * программа должна снова получить объект socket.
     * С помощью объекта Socket программа может получить входной поток и может отправить данные в выходной поток.
     * В ответ мы записываем строчку out.write().
     * В программе читается первая строка потока. После чтения отправляем ответ окончательно out.flush();
     * Если клиент отправляет запрос http://localhost:9000/?msg=Exit происходит закрытие сервера.
     *
     * @param args параметры
     */
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String str = in.readLine();
                    if (str != null && !str.isEmpty()) {
                        System.out.println(str);
                        if (str.contains("Exit")) {
                            server.close();
                        } else if (str.contains("Hello")) {
                            out.write("Hello".getBytes());
                        } else {
                            out.write("What".getBytes());
                        }
                    }
                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("Exception in log example", e);
        }
    }
}
