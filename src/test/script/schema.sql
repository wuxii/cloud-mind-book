create table mindbook_word
(
    id       serial primary key,
    text     varchar(100),
    language varchar(10),
    category varchar(10),
    ipa      varchar(100),
    remark   varchar(500)
);

create table mindbook_sentence
(
    id       serial primary key,
    sentence varchar(100),
    word_id  bigint
);

create table mindbook_paraphrase
(
    id         serial primary key,
    paraphrase varchar(100),
    word_id    bigint
);

