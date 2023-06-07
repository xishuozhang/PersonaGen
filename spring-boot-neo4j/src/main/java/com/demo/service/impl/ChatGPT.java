package com.demo.service.impl;


import com.demo.entity.GPTResponse;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Var;

public interface ChatGPT {
    // 只要输入一个参数: 提示词
    // 该方法就会发送 HTTP 请求到 OpenAI 的接口服务
    // 并将响应结果以字符串形式返回回来
    @Post(
            //https://chatgpt-api-endpoint.com/api/gpt-3.5-turbo
           url = "https://api.openai.com/v1/engines/${model}/completions",
            //rl = "https://api.openai.com/v1/engines/davinci-codex/completions",
            contentType = "application/json",
            headers = "Authorization: Bearer ${apiKey}",
            data = "{\"prompt\": \"${prompt}\", \"max_tokens\": ${maxTokens}, \"temperature\": ${temperature}}"
    )
    GPTResponse send(@Var("prompt") String prompt);
}