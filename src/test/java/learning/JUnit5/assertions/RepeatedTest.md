#@RepeatedTest in Junit 5

***@RepeatedTest*** annotation is introduced in JUnit 5. It provides us a powerful way to write any test that we want to repeat several times.

```
@RepeatedTest(3)
void repeatedTest(TestInfo testInfo) {
    System.out.println("Executing repeated test");
  
    assertEquals(2, Math.addExact(1, 1), "1 + 1 should equal 2");
}
```

**The above test will be executed three times** as if the same test was written three times.