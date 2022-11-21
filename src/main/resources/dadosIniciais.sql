INSERT INTO situacao (descricao) VALUES ('Cadastrado');
INSERT INTO situacao (descricao) VALUES ('Alterado');
INSERT INTO situacao (descricao) VALUES ('Acessado');
INSERT INTO situacao (descricao) VALUES ('Desativado');
INSERT INTO situacao (descricao) VALUES ('Reativado');
INSERT INTO situacao (descricao) VALUES ('Logado');
INSERT INTO situacao (descricao) VALUES ('Inserido');
INSERT INTO situacao (descricao) VALUES ('Removido');
INSERT INTO situacao (descricao) VALUES ('Senha alterada');
INSERT INTO situacao (descricao) VALUES ('Casal coordenador');

INSERT INTO perfil (descricao) VALUES ('Administrador');
INSERT INTO perfil (descricao) VALUES ('Vistante');

INSERT INTO usuario (nome, login, senha, ativo) VALUES ('Mateus Mattielo Nickhorn', 'mateus', '$2a$10$5R4MCT.rV02UApZ0PkSriOw970AcgavjqiYTpunFRfUOtkvAv8A6u', true);

INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES (1, 1);

INSERT INTO usuario_historico (data_hora_registro, usuario_registro_id, situacao_id, usuario_id) VALUES (now(), 1, 1, 1);