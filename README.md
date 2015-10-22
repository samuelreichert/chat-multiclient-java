# Chat Multi Client feito em Java

###Features necessárias a serem implementadas
#####As tarefas prontas já estão marcadas
* [x] - (Client Side) Conexão com servidor, definindo host e porta
* [x] - Usuário é identificado por username, então o username tem que ser único
* [x] - Envio de mensagens públicas (Todos usuários recebem)
* [ ] - Envio de mensagens privadas (Apenas usuário específico recebe)
* [ ] - Alerta de entrada/saída de usuário no log de mensagens
* [x] - Lista de usuários sempre atualizada (Apenas os usuários que estão online)

#####Tarefas que precisam ser feitas
* [x] - Pensar numa forma de armazenar o socket do usuário e o username no mesmo local (array?), quando usuário for desconectar vai facilitar remover o socket e o username.
* [ ] - Falta ainda atualizar a lista de usuários quando alguém sair
