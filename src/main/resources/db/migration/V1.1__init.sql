CREATE TABLE users (
    id bigserial primary key not null,
    username     varchar(100),
    email        varchar(80) not null,
    phone        varchar(12)
);

CREATE TABLE subscriptions (
    id bigserial primary key not null,
    title varchar(50) not null,
    description varchar(200)
);

CREATE TABLE users_subscriptions (
    user_id bigint not null references users(id),
    subscription_id bigint not null references subscriptions(id),
    primary key (user_id, subscription_id)
);