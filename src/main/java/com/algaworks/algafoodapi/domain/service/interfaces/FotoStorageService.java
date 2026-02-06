package com.algaworks.algafoodapi.domain.service.interfaces;

import java.io.InputStream;
import java.util.UUID;

public interface FotoStorageService {

    void armazenar(NovaFoto novaFoto);

    default String gerarNomeArquivo(String nomeOriginal) {
        return UUID.randomUUID().toString() + "_" + nomeOriginal;
    }


    class NovaFoto {
        private String nomeArquivo;
        private InputStream inputStream;

        public NovaFoto() {}

        public String getNomeArquivo() {
            return nomeArquivo;
        }

        public InputStream getInputStream() {
            return inputStream;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static class Builder {

            private String nomeArquivo;
            private InputStream inputStream;

            public Builder nomeArquivo(String nomeArquivo) {
                this.nomeArquivo = nomeArquivo;
                return this;
            }

            public Builder inputStream(InputStream inputStream) {
                this.inputStream = inputStream;
                return this;
            }

            public NovaFoto build() {
                NovaFoto novaFoto = new NovaFoto();
                novaFoto.nomeArquivo = this.nomeArquivo;
                novaFoto.inputStream = this.inputStream;
                return novaFoto;
            }
        }
    }
}
