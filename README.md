# Challenge Data Analysis

Nesta aplicação foi deselvolvido um Data Analysis onde deve haver input de arquivos (segue o exemplo na pasta Challenge/FileTestSystem) na pasta criada pelo sistema ~/data/in. Após o input o sistema gera um relatório com o seguinte output no caminho ~/data/out.
- 1 - Quantidade de clientes no arquivo de entrada
- 2 - Quantidade de vendedor no arquivo de entrada
- 3 - ID da venda mais cara
- 4 - O pior vendedor de todos os tempos

E o sistema ficara monitorando o caminho ~/data/in para qualquer alteração que ocorra no mesmo tanto como uma adição, modificação ou deleção de arquivos, o sistema atualizará o relatório de maneira que ele sempre tenha um output atualizado.

## Requisitos

  - [Git](https://git-scm.com/)
  - [Gradle](https://gradle.org/)

## Instalação

- Instalando Git:
```
$ apt-get install git
```

- Clone o projeto do github:
```
$ git clone https://github.com/matheusvargas481/Challenge.git
```

## Configuração

1. Acesse a pasta ~/Challenge.

2. Execute o script com o seguinte comando:
```
$ ./startDataAnalisys.sh {UTILIZANDO AS OPÇÕES ABAIXO}
```
OBS: as opções devem ser escritas em minúsculo
- build: para gerar o build da aplicação
- run: para rodar a aplicação

Pronto só acompanhar os logs pelo terminal !

Para parar a aplicação utilize o comando no terminal Ctrl+C.

## Como utilizar a aplicação

1. Insira seus arquivos no formato que dos arquivos que encontram - se na pasta ~/Challenge/FileTestSystem dentro da pasta criada pela aplicação ~/data/in.

2. Acompanhe os resultados no arquivo criado na pasta ~/out/out.dat 

## Autor

**Matheus da S. L. de Vargas** -  [GitHub](https://github.com/matheusvargas481)