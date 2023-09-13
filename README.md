# Desafio Avanade
Desafio do curso de Java da Avanade. 
Criar um jogo de RPG, que contemple as regras pré estabelecidas.
# Jogo de RPG
* As ações do jogo estão disponíveis nos endpoints (Iniciativa, Ataque, Defesa e Dano).
* A cada ação o sistema 'joga' os dados para computar a jogada. As regras para lançar os dados dependem do personagem realizando a ação e do tipo da ação.
* Todas as ocorrências da jogada (resultado das partidas, saldos de vida, etc) serão salvas e disponibilizadas no endpoint correspondente (Histórico).
* Caso a batalha não termine, o próximo turno é automaticamente aberto, com o nome do próximo atacante e os saldos de vida atualizados. 
* A batalha só termina quando um personagem fica sem pontos de vida.
* CRUD completo para manipulação dos personagens do jogo. 
# Requisitos
* Java 17
* Docker (docker compose)
* Maven
# Como executar
1) Subir o banco de dados Postgres utilizando Docker. <br />
 Rode: `docker-compose up`<br />
2) Executar o projeto SpringBoot.<br />
3) A documentação da API estará disponível em: http://localhost:8080/swagger-ui/index.html<br />
