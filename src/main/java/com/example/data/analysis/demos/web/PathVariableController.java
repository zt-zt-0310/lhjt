///*
// * Copyright 2013-2018 the original author or authors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      https://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
package com.example.data.analysis.demos.web;
//
//
////import com.volcengine.ark.runtime.model.completion.chat.ChatCompletionRequest;
////import com.volcengine.ark.runtime.model.completion.chat.ChatMessage;
////import com.volcengine.ark.runtime.model.completion.chat.ChatMessageRole;
////import com.volcengine.ark.runtime.service.ArkService;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
///**
// * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
// */
//@Controller
//public class PathVariableController {
//
//    // http://127.0.0.1:8080/user/123/roles/222
//    @RequestMapping(value = "/user/{userId}/roles/{roleId}", method = RequestMethod.GET)
//    @ResponseBody
//    public String getLogin(@PathVariable("userId") String userId, @PathVariable("roleId") String roleId) {
//        return "User Id : " + userId + " Role Id : " + roleId;
//    }
//
//    // http://127.0.0.1:8080/javabeat/somewords
//    @RequestMapping(value = "/javabeat/{regexp1:[a-z-]+}", method = RequestMethod.GET)
//    @ResponseBody
//    public String getRegExp(@PathVariable("regexp1") String regexp1) {
//        return "URI Part : " + regexp1;
//    }
//
//
//regexp1//    public static void main(String[] args) throws Exception {
////        String apiKey = System.getenv("ARK_API_KEY");
////        ArkService service = ArkService.builder().apiKey(apiKey).build();
////        //A公司的营业收入是xx，（同比）（增长，下降）xx%，利润总额是xx，（同比）（增长，下降）xx%，原因是xx，请帮我进行数据分析
////        //A公司的营业收入是xx，（同比）（增长，下降）xx%，利润总额是xx，（同比）（增长，下降）xx%，原因是xx。B公司...请帮我进行数据分析
////        System.out.println("\n----- standard request -----");
////        final List<ChatMessage> messages = new ArrayList<>();
////        final ChatMessage systemMessage = ChatMessage.builder().role(ChatMessageRole.SYSTEM).content("你是豆包，是由字节跳动开发的 AI 人工智能助手").build();
////        final ChatMessage userMessage = ChatMessage.builder().role(ChatMessageRole.USER).content("常见的十字花科植物有哪些？").build();
////        messages.add(systemMessage);
////        messages.add(userMessage);
////
////        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
////                .model("<YOUR_ENDPOINT_ID>")//这个写你的ep-开头的id
////                .messages(messages)
////                .build();
////
////        service.createChatCompletion(chatCompletionRequest).getChoices().forEach(choice -> System.out.println(choice.getMessage().getContent()));
////
////        // shutdown service
////        service.shutdownExecutor();
////    }
//}
//package com.volcengine.ark.runtime;


import com.volcengine.ark.runtime.model.completion.chat.ChatCompletionRequest;
import com.volcengine.ark.runtime.model.completion.chat.ChatMessage;
import com.volcengine.ark.runtime.model.completion.chat.ChatMessageRole;
import com.volcengine.ark.runtime.service.ArkService;
import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PathVariableController {
    static String apiKey ="8129ff2f-7450-4ae0-bd9c-4ff4c7c31caf";
    static ConnectionPool connectionPool = new ConnectionPool(5, 1, TimeUnit.SECONDS);
    static Dispatcher dispatcher = new Dispatcher();
    static ArkService service = ArkService.builder().dispatcher(dispatcher).connectionPool(connectionPool).baseUrl("https://ark.cn-beijing.volces.com/api/v3").apiKey(apiKey).build();

    public static void main(String[] args) {
        System.out.println("\n----- standard request -----");
        final List<ChatMessage> messages = new ArrayList<>();
        final ChatMessage systemMessage = ChatMessage.builder().role(ChatMessageRole.SYSTEM).content("你是豆包，是由字节跳动开发的 AI 人工智能助手").build();
        final ChatMessage userMessage = ChatMessage.builder().role(ChatMessageRole.USER).content("联环集团（单体）公司的2024-10营业收入是0.0000万元,(同比)(增长，下降)-33.460000%。请帮我进行数据分析").build();
        messages.add(systemMessage);
        messages.add(userMessage);

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model("ep-20250208140251-tssw4")
                .messages(messages)
                .build();
        final String[] a = {null};
        service.createChatCompletion(chatCompletionRequest).getChoices().forEach(choice ->
//                        a[0] = a[0] +((String) choice.getMessage().getContent())
                System.out.println(choice.getMessage().getContent())
        );
//        service.createChatCompletion(chatCompletionRequest).getChoices().forEach(choice -> System.out.println(choice.getMessage().getContent()));

//        System.out.println("\n----- streaming request -----");
//        final List<ChatMessage> streamMessages = new ArrayList<>();
//        final ChatMessage streamSystemMessage = ChatMessage.builder().role(ChatMessageRole.SYSTEM).content("你是豆包，是由字节跳动开发的 AI 人工智能助手").build();
//        final ChatMessage streamUserMessage = ChatMessage.builder().role(ChatMessageRole.USER).content("常见的十字花科植物有哪些？").build();
//        streamMessages.add(streamSystemMessage);
//        streamMessages.add(streamUserMessage);
//
//        ChatCompletionRequest streamChatCompletionRequest = ChatCompletionRequest.builder()
//                .model("ep-20250208140251-tssw4")
//                .messages(streamMessages)
//                .build();
//
//        final String[] text = {""};
//        service.streamChatCompletion(streamChatCompletionRequest)
//                .doOnError(Throwable::printStackTrace)
//                .blockingForEach(
//                        choice -> {
//                            if (choice.getChoices().size() > 0) {
//                                text[0] = text[0] +  (String) choice.getChoices().get(0).getMessage().getContent();
//                                System.out.print(choice.getChoices().get(0).getMessage().getContent());
//                            }
//                        }
//                );
//        System.out.print("这是最后加载的打印结果=========="+text);
//        System.out.print(text);

        System.out.print(a[0]);
        // shutdown service after all requests is finished
        service.shutdownExecutor();
    }

}
