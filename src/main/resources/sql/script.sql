
CREATE TABLE public.product
(
  id bigint NOT NULL,
  code character varying(255),
  description character varying(255),
  is_available boolean NOT NULL,
  name character varying(255),
  price_eur bigint NOT NULL,
  price_hrk bigint NOT NULL,
  CONSTRAINT product_pkey PRIMARY KEY (id),
  CONSTRAINT uk_h3w5r1mx6d0e5c6um32dgyjej UNIQUE (code)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.product
  OWNER TO gym_user;