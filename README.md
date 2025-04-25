# ED1-PROJETO-2-JAVA
A BR School Solutions é uma empresa de tecnologia que oferece soluções para escolas de todos
os níveis e portes, com clientes espalhados por todo o Brasil e em alguns países das Américas.

Para continuar oferecendo soluções de ponta aos seus clientes, a empresa está atualizando parte
da sua tecnologia proprietária e, após reuniões com alguns clientes, foi identificado que alguns
sistemas precisam ser modificados para atender aos novos requisitos e, nesse momento, a
empresa decidiu classificar tais sistemas como sistemas obsoletos/legados (legacy systems).

Sua equipe foi contratada para atualizar um módulo pertencente a um sistema [legado] de notas de
um dos clientes da empresa.

O sistema atual, agora considerado obsoleto, trabalha com uma base de dados em formato texto
que contém o seguinte formato:

ID da pessoa, nome da pessoa, parte inteira da nota, parte decimal da nota

Sendo:
- ID da pessoa: um número inteiro de 3 dígitos, no intervalo [000, 999].
- Nome da pessoa: um texto (string).
- Parte inteira da nota: um número inteiro de 1 ou 2 dígitos, no intervalo [0, 10].
- Parte decimal da nota: um número inteiro de 1 dígito, no intervalo [0, 9].

É importante observar que uma pessoa pode não ter uma nota atribuída (por exemplo, a pessoa
não realizou uma prova). Nesse caso, o sistema identifica a ausência de nota quando a parte inteira
e/ou parte decimal da nota tem o valor -1. A tabela a seguir resume como o sistema identifica a
ausência de nota de uma pessoa.
![image](https://github.com/user-attachments/assets/aed3aa14-b8c2-475d-9ef9-29cd00ffc93e)

Ou seja: independente da parte inteira e da parte decimal da nota, se uma das partes tiver o valor
-1, o sistema entende que a nota da pessoa não foi atribuída (ausência de nota).

Quanto ao conteúdo presente na base de dados (arquivo texto), os dados de uma pessoa são
separados pelo símbolo cerquilha (#) e cada linha do arquivo texto representa os dados de uma
pessoa.

Por exemplo, uma linha pertencente a uma base de dados desse sistema pode ser definida como:

999#Jane#8#7

Sendo:
- ID da pessoa: 999
- Nome da pessoa: Jane
- Parte inteira da nota: 8
- Parte decimal da nota: 7
Assim, podemos indicar que Jane tem ID 999 e nota 8,7.
Um exemplo de ausência de nota pode ser visto na linha a seguir:
123#John#4#-1
Nesse caso,
- ID da pessoa: 123
- Nome da pessoa: John
- Parte inteira da nota: 4
- Parte decimal da nota: -1

Com base nas regras de negócios, como a parte decimal da nota tem o valor -1, concluímos que
John, que tem o ID 123, não possui nota, mesmo que a parte inteira da nota tenha o valor 4.

É possível observar que o sistema atual usa uma representação de dados que pode gerar confusão:
Será que o -1 da parte decimal da nota de John foi um erro de digitação? Será que John realmente
não tem uma nota ou sua nota deveria ser 4,1?

Para evitar esse tipo de problema, a empresa e o cliente decidiram que é importante atualizar a
representação dos dados. Na época que o sistema original foi implantado, talvez fizesse sentido
separar a parte inteira e decimal da nota de uma pessoa, usando um número inteiro para cada
parte. No entanto, empresa e cliente optaram por substituir esses dois números inteiros por um
único número real (float).

Para o caso “ausência de nota”, o cliente pediu para usar o valor 99.9.

Além da nota, o cliente pediu para a empresa atualizar a representação do ID da pessoa: todo ID
deve ter um prefixo no formato {YY}.S{N}-, sendo:

- {YY}: Ano atual, com dois dígitos (por exemplo, 23).
- {N}: 1 para primeiro semestre ou 2 para segundo semestre do ano.
- Os outros três caracteres (.S-) são sempre fixos.

E, ao invés de usar a cerquilha (#) como separador de dados, a empresa optou por usar o símbolo
ponto-e-vírgula (;) como separador de dados, seguindo um dos formatos de arquivo CSV.

Cada linha do arquivo texto continua representando os dados de uma pessoa.

Usando Jane e John como exemplo, os dados dessas pessoas, na nova base de dados, seriam
descritos da seguinte maneira, respectivamente:

23.S1-999;Jane;8.7
23.S1-123;John;99.9

Portanto, o novo sistema deve trabalhar com uma base de dados em formato texto que contém
o seguinte formato:

ID da pessoa, nome da pessoa, nota da pessoa

Sendo:
- ID da pessoa: um texto (string) que começa com 23.S1- seguido de 3 dígitos, no intervalo [000, 999] (antigo ID da pessoa).
- Nome da pessoa: um texto (string).
- Nota da pessoa: um número real (float), no intervalo [0.0, 10.0] ou 99.9 no caso de ausência de nota. O número é a combinação das partes inteira e decimal da nota da pessoa.
- Cada dado é separado pelo símbolo ponto-e-vírgula (;).
- Cada linha do arquivo texto representa uma pessoa.
