-- Script SQL para criar a tabela Course
CREATE TABLE IF NOT EXISTS course (
    id UUID PRIMARY KEY NOT NULL,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(10) NOT NULL,
    status VARCHAR(10) DEFAULT 'Active',
    description VARCHAR(300)
);

-- Script SQL para criar a tabela Vote
CREATE TABLE IF NOT EXISTS vote (
    id UUID PRIMARY KEY NOT NULL,
    course_id UUID,
    host VARCHAR(255) NOT NULL,
    type VARCHAR(10) not NULL,
    insert_date TIMESTAMPTZ NOT NULL DEFAULT now(),
    update_date TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT fk_vote_course_id FOREIGN KEY (course_id) REFERENCES Course(id),
    CONSTRAINT uq_votacao_host_course UNIQUE (host, course_id)
);

-- Script SQL para criar a tabela VoteHistory
CREATE TABLE IF NOT EXISTS voteHistory (
    id UUID PRIMARY KEY,
    course_id UUID,
    host VARCHAR(255) NOT NULL,
    type VARCHAR(10) not NULL,
    update_date TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT fk_votehistory_course FOREIGN KEY (course_id) REFERENCES Course(id)
);