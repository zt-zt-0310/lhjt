package com.example.data.analysis.utils;

import com.volcengine.ark.runtime.model.completion.chat.ChatCompletionRequest;
import com.volcengine.ark.runtime.model.completion.chat.ChatMessage;
import com.volcengine.ark.runtime.model.completion.chat.ChatMessageRole;
import com.volcengine.ark.runtime.service.ArkService;
import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author zt
 * @Description TODO
 * @Time 2025/2/8 14:29
 */
@Component
public class ChatCompletionsExample {
    static String apiKey ="456cc65f-99a2-4e80-a173-89bca9b5fea2";
//    static String apiKey ="8129ff2f-7450-4ae0-bd9c-4ff4c7c31caf";
//    export ARK_API_KEY="456cc65f-99a2-4e80-a173-89bca9b5fea2"
    static ConnectionPool connectionPool = new ConnectionPool(5, 1, TimeUnit.SECONDS);
    static Dispatcher dispatcher = new Dispatcher();
    static ArkService service = ArkService.builder().dispatcher(dispatcher).connectionPool(connectionPool).baseUrl("https://ark.cn-beijing.volces.com/api/v3").apiKey(apiKey).build();

    public String chatCompletions(String manage) {
        System.out.println("\n----- standard request -----");
        final List<ChatMessage> messages = new ArrayList<>();
        final ChatMessage systemMessage = ChatMessage.builder().role(ChatMessageRole.SYSTEM).content("你是豆包，是由字节跳动开发的 AI 人工智能助手").build();
        final ChatMessage userMessage = ChatMessage.builder().role(ChatMessageRole.USER).content(manage).build();
        final String[] text = {""};
        messages.add(systemMessage);
        messages.add(userMessage);

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model("ep-20250214151113-zltkp")
                .messages(messages)
                .build();

        service.createChatCompletion(chatCompletionRequest).getChoices().forEach(choice ->
                        text[0] = text[0] +((String) choice.getMessage().getContent())
//                System.out.println(choice.getMessage().getContent())
        );

        System.out.println(text);

        return text[0];
    }


}
