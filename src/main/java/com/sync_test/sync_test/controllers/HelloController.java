package com.sync_test.sync_test.controllers;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/test-multiple-threads")
  public String multipleThreadsOfsayHello() {
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    return "Hello, world!";
  }

  private final ExecutorService executorService = Executors.newSingleThreadExecutor();

  @GetMapping("/test-single-thread")
  public CompletableFuture<String> singleThreadOfsayHello() {
    return CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(10000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      return "Hello, world!";

    }, executorService);
  }

  @GetMapping("/test-async-single-thread")
  public CompletableFuture<String> asyncSingleThreadOfSayHello() {
    return CompletableFuture.supplyAsync(() -> {
      return "Hello, world!";
    }, CompletableFuture.delayedExecutor(10, TimeUnit.SECONDS, executorService));
  }

}
