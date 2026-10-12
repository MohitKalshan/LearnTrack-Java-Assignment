# JVM Basics

## What is JDK, JRE, and JVM?

### JVM — Java Virtual Machine
The JVM is the engine that actually **runs** your Java programs. When you compile a `.java` file, it gets converted into **bytecode** (a `.class` file). The JVM reads this bytecode and translates it into machine-specific instructions that your operating system can understand. Every platform (Windows, macOS, Linux) has its own JVM implementation, but they all understand the same bytecode — which is the key to Java's portability.

### JRE — Java Runtime Environment
The JRE is a **package that contains the JVM** plus all the standard libraries (like `java.util`, `java.io`, etc.) that your Java programs need to run. If you only want to *run* Java programs (not write them), the JRE is all you need. Think of it as: **JRE = JVM + Standard Libraries**.

### JDK — Java Development Kit
The JDK is the **full toolkit for Java developers**. It includes everything in the JRE, plus development tools like the Java compiler (`javac`), the debugger (`jdb`), and other utilities. If you want to *write and compile* Java programs, you need the JDK. Think of it as: **JDK = JRE + Development Tools (compiler, debugger, etc.)**.

### Summary

| Component | Contains | Purpose |
|-----------|----------|---------|
| **JVM** | Virtual machine engine | Executes bytecode |
| **JRE** | JVM + standard libraries | Runs Java programs |
| **JDK** | JRE + dev tools (javac, jdb) | Develops & runs Java programs |

---

## What is Bytecode?

When you write Java code in a `.java` file and compile it with `javac`, the compiler does **not** produce machine code that your specific CPU understands. Instead, it produces an intermediate format called **bytecode**, stored in `.class` files.

Bytecode is a set of instructions designed for the JVM — not for any specific hardware. The JVM then interprets (or JIT-compiles) this bytecode into native machine code at runtime. This extra step is what makes Java platform-independent: the same `.class` file can run on any machine that has a JVM installed.

```
YourCode.java  →  javac  →  YourCode.class (bytecode)  →  JVM  →  Runs on any OS
```

---

## What Does "Write Once, Run Anywhere" Mean?

"Write Once, Run Anywhere" (WORA) is Java's founding promise. It means that once you write and compile a Java program on one platform (say, your macOS laptop), the resulting bytecode can run on **any other platform** (Windows, Linux, etc.) without needing to recompile or change your code.

This works because the bytecode is platform-neutral — it targets the JVM, not a specific operating system or CPU architecture. As long as the target machine has a compatible JVM installed, your program will run the same way. This is a major advantage over languages like C or C++, where you typically need to recompile your code for each target platform.

In practice, WORA means you can develop on a Mac, test on Linux, and deploy to a Windows server — all from the same compiled `.class` files.
