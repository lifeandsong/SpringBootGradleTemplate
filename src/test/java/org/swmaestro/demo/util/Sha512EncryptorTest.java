package org.swmaestro.demo.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Sha512Encryptor 테스트")
public class Sha512EncryptorTest {

    @Test
    @DisplayName("SHA-512 단방향 암호화 테스트")
    public void encrypt() {
        Sha512Encryptor sha256 = new Sha512Encryptor();
        assertEquals(sha256.encrypt("passwordTest12#$"), "ab3fc0bdf85c276ac87ee84e043384eca9972f9d2eb561a865eae63d83f989cc286da27db9ec7b3c0af976a48423d0b30b0c25f2f0baed594b318b65b2d86853");
    }
}
