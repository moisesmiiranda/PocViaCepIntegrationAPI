-- Criação da tabela tb_address
CREATE TABLE tb_address (
    cep VARCHAR(8) NOT NULL PRIMARY KEY,
    logradouro VARCHAR(255) NOT NULL,
    complemento VARCHAR(255),
    bairro VARCHAR(255) NOT NULL,
    localidade VARCHAR(255) NOT NULL,
    uf VARCHAR(2) NOT NULL,
    ibge VARCHAR(7),
    gia VARCHAR(4),
    ddd VARCHAR(3),
    siafi VARCHAR(4)
);

-- Inserção de dados na tabela tb_address
INSERT INTO tb_address (
    cep,
    logradouro,
    complemento,
    bairro,
    localidade,
    uf,
    ibge,
    gia,
    ddd,
    siafi
) VALUES (
    '12345001', 'Rua A1', 'Complemento 1','Bairro 01', 'Cidade 01', 'CE', '7777777', '', '00', '3333'
);

INSERT INTO tb_address (
    cep,
    logradouro,
    complemento,
    bairro,
    localidade,
    uf,
    ibge,
    gia,
    ddd,
    siafi
) VALUES (
    '12345002', 'Rua A2', 'Complemento 2','Bairro 02', 'Cidade 02', 'SP', '8888888', '', '00', '4444'
);

INSERT INTO tb_address (
    cep,
    logradouro,
    complemento,
    bairro,
    localidade,
    uf,
    ibge,
    gia,
    ddd,
    siafi
) VALUES (
    '12345003', 'Rua A3', 'Complemento 3','Bairro 03', 'Cidade 03', 'RJ', '9999999', '', '00', '5555'
);