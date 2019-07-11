# 2019-1-dcc193-trb3-2019-1-trbo2-dupla-aline_e_yan2


Autores:

Aline De Paula Sotte (alinedpaso@gmail.com) Yan de Paiva (gckael@gmail.com)

github: https://github.com/ufjf-dcc193/2019-1-dcc193-trb3-2019-1-trbo2-dupla-aline_e_yan2

Instalação:

Foi utilizado o VS Code para a implemetação do Trabalho 3.

Tecnologia:

-- Spring Boot,JPA,módulos Web

Bancos

H2 local
PostgreSql produção

Thymeleaf (View)
Estilo: bootstrap

Foi utilizado o Heruko ( https://trabalhofeitoopcional.herokuapp.com/ )

Especificações:

Sistema para gerir atendimento. O atendente faz o cadastro na página principal, caso não tenha, faz o login e entra na página principal.

Nesta página principal encontra um painel com Atendimento, Usuário, Atendente e Categoria. Em cada página o atendente pode realizar ações de acordo com a necessidade do seu trabalho.

Para excluir usuário, categoria e atendente foi necessário tratar cada caso. Para usuário setamos o valor do atendimento para null. Para categoria e atendente é necessário escolher um novo atendente e uma nova categoria antes de excluir.