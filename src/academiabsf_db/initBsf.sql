
CREATE DATABASE IF NOT EXISTS sistemaBSF;
USE sistemaBSF;

-- TABELA: sistema_bsf
USE sistemaBSF;

CREATE TABLE IF NOT EXISTS usuarios_bsf (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    login VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_nome (nome),
    INDEX idx_login (login),
    INDEX idx_email (email)
    );

-- TABELA: treino_bsf
USE sistemaBSF;

CREATE TABLE IF NOT EXISTS treino_bsf (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_execucao DATE NOT NULL,
    usuario_id INT NOT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_nome (nome),
    INDEX idx_data_execucao (data_execucao),
    INDEX idx_usuario_id (usuario_id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios_bsf(id) ON DELETE CASCADE ON UPDATE CASCADE
    );

-- INSERÇÃO DE DADOS DE TESTE

INSERT INTO usuarios_bsf (nome, login, senha, email) VALUES
    ('Ana Silva Santos', 'ana.silva', 'senha123', 'ana.silva@email.com'),
    ('Carlos Roberto Lima', 'carlos.lima', 'pass456', 'carlos.lima@gmail.com'),
    ('Mariana Costa Oliveira', 'mari.costa', 'abc789', 'mariana.costa@hotmail.com'),
    ('João Pedro Ferreira', 'joao.pedro', 'minhasenha', 'joao.ferreira@yahoo.com'),
    ('Fernanda Alves Souza', 'fernanda.alves', 'senha2024', 'fernanda.souza@outlook.com'),
    ('Lucas Mendes Ribeiro', 'lucas.mendes', 'password123', 'lucas.ribeiro@email.com'),
    ('Juliana Barbosa Cruz', 'juliana.cruz', 'jul2024', 'juliana.barbosa@gmail.com'),
    ('Rafael Dias Moreira', 'rafael.dias', 'rafa456', 'rafael.moreira@hotmail.com'),
    ('Camila Rodrigues Nunes', 'camila.nunes', 'cam789', 'camila.rodrigues@yahoo.com'),
    ('Bruno Cardoso Freitas', 'bruno.cardoso', 'bruno2024', 'bruno.freitas@outlook.com'),
    ('Larissa Pereira Gomes', 'larissa.gomes', 'lari123', 'larissa.pereira@email.com'),
    ('Thiago Santana Rocha', 'thiago.rocha', 'thiago456', 'thiago.santana@gmail.com'),
    ('Priscila Machado Silva', 'priscila.silva', 'pri789', 'priscila.machado@hotmail.com'),
    ('Gustavo Araujo Lopes', 'gustavo.lopes', 'gus2024', 'gustavo.araujo@yahoo.com'),
    ('Beatriz Campos Martins', 'beatriz.martins', 'bia123', 'beatriz.campos@outlook.com'),
    ('Rodrigo Nascimento Costa', 'rodrigo.costa', 'rod456', 'rodrigo.nascimento@email.com'),
    ('Aline Carvalho Reis', 'aline.reis', 'aline789', 'aline.carvalho@gmail.com'),
    ('Eduardo Santos Pinto', 'eduardo.pinto', 'edu2024', 'eduardo.santos@hotmail.com'),
    ('Patrícia Vieira Aguiar', 'patricia.aguiar', 'pat123', 'patricia.vieira@yahoo.com'),
    ('Mateus Correia Teixeira', 'mateus.teixeira', 'mat456', 'mateus.correia@outlook.com');

INSERT INTO treino_bsf (nome, data_execucao, usuario_id) VALUES
    ('Treino de Peito e Tríceps', '2024-11-05', 14),
    ('Corrida 8km', '2024-11-12', 3),
    ('HIIT 25 minutos', '2024-11-18', 7),
    ('Treino de Costas e Bíceps', '2024-11-21', 19),
    ('Yoga Flow', '2024-11-28', 2),
    ('Treino de Pernas', '2024-12-02', 11),
    ('Natação 800m', '2024-12-05', 16),
    ('Treino Funcional', '2024-12-08', 5),
    ('Caminhada 45min', '2024-12-11', 20),
    ('Treino de Ombros', '2024-12-14', 8),
    ('Pilates Reformer', '2024-12-17', 1),
    ('Crossfit WOD', '2024-12-20', 13),
    ('Treino Push/Pull', '2024-12-23', 4),
    ('Boxe', '2024-12-26', 9),
    ('Treino de Glúteos', '2024-12-29', 17),
    ('Ciclismo 15km', '2025-01-01', 6),
    ('Treino Full Body', '2025-01-04', 12),
    ('Dança Zumba', '2025-01-07', 18),
    ('Treino de Abdômen', '2025-01-10', 10),
    ('Escalada Indoor', '2025-01-13', 15),
    ('Treino de Panturrilha', '2024-11-08', 3),
    ('Corrida Intervalada', '2024-11-15', 7),
    ('Treino Upper Body', '2024-11-22', 1),
    ('Aqua Aeróbica', '2024-11-29', 14),
    ('Treino de Quadríceps', '2024-12-03', 19),
    ('Spinning 40min', '2024-12-06', 11),
    ('Treino Isométrico', '2024-12-09', 2),
    ('Muay Thai', '2024-12-12', 16),
    ('Treino de Posteriores', '2024-12-15', 20),
    ('Yoga Hatha', '2024-12-18', 5),
    ('Treino de Bíceps', '2024-12-21', 8),
    ('Jump Fit', '2024-12-24', 13),
    ('Treino Lower Body', '2024-12-27', 9),
    ('Corrida 3km', '2024-12-30', 4),
    ('Treino de Tríceps', '2025-01-02', 17),
    ('Natação Livre', '2025-01-05', 6),
    ('Treino Core', '2025-01-08', 12),
    ('Kickboxing', '2025-01-11', 18),
    ('Treino de Deltoides', '2025-01-14', 10),
    ('Caminhada na Esteira', '2024-11-10', 15),
    ('Treino de Peito', '2024-11-17', 7),
    ('Pilates Solo', '2024-11-24', 3),
    ('Treino Metabólico', '2024-12-01', 1),
    ('Remo 2000m', '2024-12-04', 14),
    ('Treino de Costas', '2024-12-07', 19),
    ('Body Pump', '2024-12-10', 11),
    ('Treino de Flexibilidade', '2024-12-13', 2),
    ('Corrida na Praia', '2024-12-16', 16),
    ('Treino de Força', '2024-12-19', 20),
    ('Ashtanga Yoga', '2024-12-22', 5),
    ('Treino Circuit', '2024-12-25', 8),
    ('Elíptico 30min', '2024-12-28', 13),
    ('Treino de Resistência', '2024-12-31', 9),
    ('Natação Borboleta', '2025-01-03', 4),
    ('Treino de Mobilidade', '2025-01-06', 17),
    ('TRX Training', '2025-01-09', 6),
    ('Treino Explosivo', '2025-01-12', 12),
    ('Step Aeróbico', '2025-01-15', 18),
    ('Treino de Trapézio', '2024-11-07', 10),
    ('Corrida Cross Country', '2024-11-14', 15),
    ('Treino Calistenia', '2024-11-21', 7),
    ('Hidroginástica', '2024-11-28', 3),
    ('Treino de Antebraço', '2024-12-02', 1),
    ('Bike Indoor', '2024-12-05', 14),
    ('Treino Pliométrico', '2024-12-08', 19),
    ('Jiu-Jitsu', '2024-12-11', 11),
    ('Treino de Lombar', '2024-12-14', 2),
    ('Corrida Fartlek', '2024-12-17', 16),
    ('Treino Suspenso', '2024-12-20', 20),
    ('Power Yoga', '2024-12-23', 5),
    ('Treino de Cardio', '2024-12-26', 8),
    ('Natação Costas', '2024-12-29', 13),
    ('Treino de Equilíbrio', '2025-01-01', 9),
    ('Capoeira', '2025-01-04', 4),
    ('Treino de Agilidade', '2025-01-07', 17),
    ('Spinning Hill', '2025-01-10', 6),
    ('Treino Tabata', '2025-01-13', 12),
    ('Ballet Fitness', '2025-01-16', 18),
    ('Treino de Resistência Muscular', '2024-11-09', 10),
    ('Corrida Tempo', '2024-11-16', 15),
    ('Treino Compound', '2024-11-23', 7),
    ('Aqua Running', '2024-11-30', 3),
    ('Treino de Potência', '2024-12-03', 1),
    ('Ciclismo Mountain Bike', '2024-12-06', 14),
    ('Treino Unilateral', '2024-12-09', 19),
    ('Krav Maga', '2024-12-12', 11),
    ('Treino de Estabilização', '2024-12-15', 2),
    ('Corrida Regenerativa', '2024-12-18', 16),
    ('Treino Kettlebell', '2024-12-21', 20),
    ('Vinyasa Yoga', '2024-12-24', 5),
    ('Treino de Velocidade', '2024-12-27', 8),
    ('Natação Peito', '2024-12-30', 13),
    ('Treino de Coordenação', '2025-01-02', 9),
    ('Taekwondo', '2025-01-05', 4),
    ('Treino de Endurance', '2025-01-08', 17),
    ('RPM 45min', '2025-01-11', 6),
    ('Treino AMRAP', '2025-01-14', 12),
    ('Pole Dance', '2025-01-17', 18),
    ('Treino de Reabilitação', '2024-11-11', 10),
    ('Corrida de Ladeira', '2024-11-18', 15),
    ('Treino Strongman', '2024-11-25', 7),
    ('Aqua Zumba', '2024-12-01', 3),
    ('Treino de Definição', '2024-12-04', 1),
    ('Bike Outdoor', '2024-12-07', 14),
    ('Treino Bulgarian', '2024-12-10', 19),
    ('Wrestling', '2024-12-13', 11);


-- CONSULTAS PARA TESTE

SELECT 'USUÁRIOS' as TABELA;
SELECT * FROM usuarios_bsf ORDER BY nome;

SELECT 'TREINOS' as TABELA;
SELECT treino.*, usuario.nome as usuario_nome
FROM treino_bsf treino
         INNER JOIN usuarios_bsf usuario ON treino.usuario_id = usuario.id
ORDER BY treino.data_execucao DESC;

-- Estatísticas
SELECT 'ESTATÍSTICAS' as INFO;
SELECT
    (SELECT COUNT(*) FROM usuarios_bsf) as total_usuarios,
    (SELECT COUNT(*) FROM treino_bsf) as total_treinos,
    ROUND((SELECT COUNT(*) FROM treino_bsf) / (SELECT COUNT(*) FROM usuarios_bsf), 2) as media_treinos_por_usuario;

-- Treinos por usuário
SELECT
    usuario.nome,
    COUNT(treino.id) as total_treinos
FROM usuarios_bsf usuario
         LEFT JOIN treino_bsf treino ON usuario.id = treino.usuario_id
GROUP BY usuario.id, usuario.nome
ORDER BY total_treinos DESC, usuario.nome;


-- VIEWS ÚTEIS


-- View para relatórios de treinos com dados do usuário
CREATE OR REPLACE VIEW vw_treinos_completos AS
SELECT
    treino.id as treino_id,
    treino.nome as treino_nome,
    treino.data_execucao,
    treino.data_cadastro as treino_cadastro,
    usuario.id as usuario_id,
    usuario.nome as usuario_nome,
    usuario.login as usuario_login,
    usuario.email as usuario_email
FROM treino_bsf treino
         INNER JOIN usuarios_bsf usuario ON treino.usuario_id = usuario.id;

-- View para estatísticas por usuário
CREATE OR REPLACE VIEW vw_estatisticas_usuario AS
SELECT
    usuario.id,
    usuario.nome,
    usuario.login,
    usuario.email,
    usuario.data_cadastro,
    COUNT(treino.id) as total_treinos,
    MIN(treino.data_execucao) as primeiro_treino,
    MAX(treino.data_execucao) as ultimo_treino
FROM usuarios_bsf usuario
         LEFT JOIN treino_bsf treino ON usuario.id = treino.usuario_id
GROUP BY usuario.id, usuario.nome, usuario.login, usuario.email, usuario.data_cadastro;


-- Procedure para buscar treinos de um usuário em um período
DELIMITER //
CREATE PROCEDURE sp_treinos_usuario_periodo(
    IN p_usuario_id INT,
    IN p_data_inicio DATE,
    IN p_data_fim DATE
)
BEGIN
SELECT
    treino.*,
    usuario.nome as usuario_nome
FROM treino_bsf treino
         INNER JOIN usuarios_bsf usuario ON treino.usuario_id = usuario.id
WHERE treino.usuario_id = p_usuario_id
  AND treino.data_execucao BETWEEN p_data_inicio AND p_data_fim
ORDER BY treino.data_execucao DESC;
END //
DELIMITER ;

-- Procedure para estatísticas gerais
DELIMITER //
CREATE PROCEDURE sp_estatisticas_gerais()
BEGIN
SELECT
    'Usuários' as categoria,
    COUNT(*) as total
FROM usuarios_bsf

UNION ALL

SELECT
    'Treinos' as categoria,
    COUNT(*) as total
FROM treino_bsf

UNION ALL

SELECT
    'Média treinos/usuário' as categoria,
    ROUND(
            (SELECT COUNT(*) FROM treino_bsf) /
            (SELECT COUNT(*) FROM usuarios_bsf), 2
    ) as total;
END //
DELIMITER ;


