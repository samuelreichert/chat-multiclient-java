# Chat Multi Client feito em Java

###Features necessárias a serem implementadas
#####As tarefas prontas já estão marcadas
* [x] - (Client Side) Conexão com servidor, definindo host e porta
* [x] - Usuário é identificado por username, então o username tem que ser único
* [x] - Envio de mensagens públicas (Todos usuários recebem)
* [ ] - Envio de mensagens privadas (Apenas usuário específico recebe)
* [ ] - Alerta de entrada/saída de usuário no log de mensagens
* [ ] - Lista de usuários sempre atualizada (Apenas os usuários que estão online)

#####Tarefas que precisam ser feitas / em andamento
* [ ] - Quando clicar em desconectar tem que enviar para o server o aviso
* [ ] - Pensar numa forma de armazenar o socket do usuário e o username no mesmo local (array?), quando usuário for desconectar vai facilitar remover o socket e o username. 

#### Ideia
Na lista de usuários online poderia haver um 'geral', que serviria para enviar mensagens públicas, acredito que facilitaria na troca entre uma tela e outra.

testestestes
