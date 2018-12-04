package sample.verify;

import java.io.File;

public interface Verificator {
    void verify(File fileToVerify, VerificatorCallback callback);
}
