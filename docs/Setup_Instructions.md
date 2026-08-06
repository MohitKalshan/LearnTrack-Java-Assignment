# Setup Instructions

## JDK Version Used

This project was developed and tested with:

- **JDK 17** (LTS — Long Term Support)

Any JDK version 17 or later will work.

---

## Step 1: Install JDK

### macOS
```bash
# Using Homebrew
brew install openjdk@17

# Add to PATH (if not automatic)
echo 'export PATH="/opt/homebrew/opt/openjdk@17/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
```

### Windows
1. Download JDK 17 from [Oracle](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or [Adoptium](https://adoptium.net/)
2. Run the installer
3. Add `JAVA_HOME` to your environment variables (the installer usually does this)
4. Add `%JAVA_HOME%\bin` to your `PATH`

### Linux (Ubuntu/Debian)
```bash
sudo apt update
sudo apt install openjdk-17-jdk
```

---

## Step 2: Verify Installation

Open a terminal and run:

```bash
java -version
```

Expected output (version numbers may vary):
```
openjdk version "17.0.x" 2024-xx-xx
OpenJDK Runtime Environment ...
OpenJDK 64-Bit Server VM ...
```

Also verify the compiler:
```bash
javac -version
```

Expected output:
```
javac 17.0.x
```

---

## Step 3: Hello World — Verifying Your Setup

Create a file called `HelloWorld.java`:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World! Java is set up correctly.");
    }
}
```

### Compile
```bash
javac HelloWorld.java
```

This produces a `HelloWorld.class` file (bytecode).

### Run
```bash
java HelloWorld
```

Expected output:
```
Hello, World! Java is set up correctly.
```

If you see this message, your JDK is correctly installed and configured! 🎉

---

## Step 4: Compile and Run LearnTrack

From the project root (`LearnTrack/`):

### Compile all source files
```bash
javac -d out src/com/airtribe/learntrack/entity/*.java \
              src/com/airtribe/learntrack/exception/*.java \
              src/com/airtribe/learntrack/util/*.java \
              src/com/airtribe/learntrack/service/*.java \
              src/com/airtribe/learntrack/ui/*.java
```

### Run the application
```bash
java -cp out com.airtribe.learntrack.ui.Main
```

You should see the LearnTrack welcome screen and main menu.
