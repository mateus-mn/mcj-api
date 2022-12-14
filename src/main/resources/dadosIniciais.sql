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
INSERT INTO perfil (descricao) VALUES ('Visitante');

INSERT INTO usuario (nome, login, senha, ativo) VALUES ('Mateus Mattielo Nickhorn', 'mateus', '$2a$10$5R4MCT.rV02UApZ0PkSriOw970AcgavjqiYTpunFRfUOtkvAv8A6u', true);

INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES (1, 1);

INSERT INTO usuario_historico (data_hora_registro, usuario_registro_id, situacao_id, usuario_id) VALUES (now(), 1, 1, 1);

INSERT INTO sexo (descricao, sigla) VALUES ('Masculino', 'M');
INSERT INTO sexo (descricao, sigla) VALUES ('Feminino', 'F');

INSERT INTO pais (nome, sigla) VALUES ('Brasil', 'BR');

INSERT INTO estado (nome, sigla, pais_id) VALUES ('Acre', 'AC', 1);
INSERT INTO estado (nome, sigla, pais_id) VALUES ('Alagoas', 'AL', 1);
INSERT INTO estado (nome, sigla, pais_id) VALUES ('Amapá', 'AP', 1);
INSERT INTO estado (nome, sigla, pais_id) VALUES ('Amazonas', 'AM', 1);
INSERT INTO estado (nome, sigla, pais_id) VALUES ('Bahia', 'BA', 1);
INSERT INTO estado (nome, sigla, pais_id) VALUES ('Ceará', 'CE', 1);
INSERT INTO estado (nome, sigla, pais_id) VALUES ('Distrito Federal', 'DF', 1);
INSERT INTO estado (nome, sigla, pais_id) VALUES ('Espírito Santo', 'ES', 1);
INSERT INTO estado (nome, sigla, pais_id) VALUES ('Goiás', 'GO', 1);
INSERT INTO estado (nome, sigla, pais_id) VALUES ('Maranhão', 'MA', 1);
INSERT INTO estado (nome, sigla, pais_id) VALUES ('Mato Grosso', 'MT', 1);
INSERT INTO estado (nome, sigla, pais_id) VALUES ('Mato Grosso do Sul', 'MS', 1);
INSERT INTO estado (nome, sigla, pais_id) VALUES ('Minas Gerais', 'MG', 1);
INSERT INTO estado (nome, sigla, pais_id) VALUES ('Pará', 'PA', 1);
INSERT INTO estado (nome, sigla, pais_id) VALUES ('Paraíba', 'PB', 1);
INSERT INTO estado (nome, sigla, pais_id) VALUES ('Paraná', 'PR', 1);
INSERT INTO estado (nome, sigla, pais_id) VALUES ('Pernambuco', 'PE', 1);
INSERT INTO estado (nome, sigla, pais_id) VALUES ('Piauí', 'PI', 1);
INSERT INTO estado (nome, sigla, pais_id) VALUES ('Rio de Janeiro', 'RJ', 1);
INSERT INTO estado (nome, sigla, pais_id) VALUES ('Rio Grande do Norte', 'RN', 1);
INSERT INTO estado (nome, sigla, pais_id) VALUES ('Rio Grande do Sul', 'RS', 1);
INSERT INTO estado (nome, sigla, pais_id) VALUES ('Rondônia', 'RO', 1);
INSERT INTO estado (nome, sigla, pais_id) VALUES ('Roraima', 'RR', 1);
INSERT INTO estado (nome, sigla, pais_id) VALUES ('Santa Catarina', 'SC', 1);
INSERT INTO estado (nome, sigla, pais_id) VALUES ('São Paulo', 'SP', 1);
INSERT INTO estado (nome, sigla, pais_id) VALUES ('Sergipe', 'SE', 1);
INSERT INTO estado (nome, sigla, pais_id) VALUES ('Tocantins', 'TO', 1);

INSERT INTO cidade (nome, estado_id, ativo) VALUES ('Passo Fundo', 21, true);

INSERT INTO cidade_historico (data_hora_registro, cidade_id, situacao_id, usuario_registro_id) VALUES (now(), 1, 1, 1);