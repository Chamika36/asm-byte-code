package org.example;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.FileOutputStream;

public class Main {
    public static void main(String[] args) {
        try {
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);

            cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "GeneratedClass", null, "java/lang/Object", null);

            MethodVisitor helloMethod = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "hello", "()V", null, null);
            helloMethod.visitCode();
            helloMethod.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            helloMethod.visitLdcInsn("Hello from the hello method!");
            helloMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            helloMethod.visitInsn(Opcodes.RETURN);
            helloMethod.visitMaxs(0, 0);
            helloMethod.visitEnd();

            MethodVisitor mainMethod = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            mainMethod.visitCode();
            mainMethod.visitMethodInsn(Opcodes.INVOKESTATIC, "GeneratedClass", "hello", "()V", false);
            mainMethod.visitInsn(Opcodes.RETURN);
            mainMethod.visitMaxs(0, 0);
            mainMethod.visitEnd();

            cw.visitEnd();

            byte[] bytes = cw.toByteArray();
            try (FileOutputStream stream = new FileOutputStream("GeneratedClass.class")) {
                stream.write(bytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
