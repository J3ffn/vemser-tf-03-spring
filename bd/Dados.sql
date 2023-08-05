-- Hospital --
INSERT INTO Hospital(id_hospital, nome)
VALUES(1, 'WB Health');

-- Pessoa --
INSERT INTO Pessoa(id_pessoa, nome, cep, data_nascimento, cpf, salario_mensal)
VALUES(seq_pessoa.nextval, 'Gertrudes', 99999999, TO_DATE('11-02-1949','dd-MM-yyyy'), '33333333333', 0.00);

INSERT INTO Pessoa(id_pessoa, nome, cep, data_nascimento, cpf, salario_mensal)
VALUES(seq_pessoa.nextval, 'Mauricio', 99998999, TO_DATE('12-04-2022','dd-MM-yyyy'), '44444444444', 1000.30);

INSERT INTO Pessoa(id_pessoa, nome, cep, data_nascimento, cpf, salario_mensal)
VALUES(seq_pessoa.nextval, 'Larissa', 99988999, TO_DATE('22-11-1999','dd-MM-yyyy'), '88888888888', 3000.00);

INSERT INTO Pessoa(id_pessoa, nome, cep, data_nascimento, cpf, salario_mensal)
VALUES(seq_pessoa.nextval, 'Marcos', 99988999, TO_DATE('22-11-1999','dd-MM-yyyy'), '77777777777', 300.10);

-- Paciente --
INSERT INTO PACIENTE (id_paciente, id_hospital, id_pessoa)
VALUES (SEQ_PACIENTE.nextval, 1, 1);

-- Funcionário --
INSERT INTO Funcionario (id_funcionario, id_hospital, id_pessoa)
VALUES (SEQ_FUNCIONARIO.nextval, 1, 2);

-- Médico --
INSERT INTO Medico (id_medico, id_pessoa, id_hospital, crm)
	VALUES(SEQ_MEDICO.nextval,  3, 1, 'SP-1234567/89');

INSERT INTO Medico (id_medico, id_pessoa, id_hospital, crm)
	VALUES(SEQ_MEDICO.nextval,  4, 1, 'AM-1234567/82');

-- Atendimento --
INSERT INTO Atendimento (id_atendimento, id_hospital, id_paciente, id_medico, data_atendimento, laudo, tipo_de_atendimento, valor_atendimento)
	VALUES(SEQ_ATENDIMENTO.nextval, 1, 1, 1, TO_DATE('27-09-2023', 'dd-mm-yyyy'), 'dor na consciência', 'CONSULTA', 200.00);

INSERT INTO Atendimento (id_atendimento, id_hospital, id_paciente, id_medico, data_atendimento, laudo, tipo_de_atendimento, valor_atendimento)
	VALUES(SEQ_ATENDIMENTO.nextval, 1, 1, 2, TO_DATE('16-08-2023', 'dd-mm-yyyy'), 'dor de cotovelo', 'CIRURGIA', 3000.00);
