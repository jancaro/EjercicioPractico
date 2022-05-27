--
-- PostgreSQL database dump
--

-- Dumped from database version 14.3
-- Dumped by pg_dump version 14.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE banco;
--
-- Name: banco; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE banco WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Ecuador.1252';


ALTER DATABASE banco OWNER TO postgres;

\connect banco

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente (
    id character varying(255) NOT NULL,
    direccion character varying(255),
    edad integer,
    genero character varying(255),
    identificacion character varying(255),
    nombre character varying(255),
    telefono character varying(255),
    contrasena character varying(255) NOT NULL,
    estado boolean
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- Name: cuenta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cuenta (
    id character varying(255) NOT NULL,
    estado boolean,
    numero_cuenta character varying(255),
    saldo_inicial numeric(19,2),
    tipo_cuenta character varying(255),
    cliente_id character varying(255)
);


ALTER TABLE public.cuenta OWNER TO postgres;

--
-- Name: movimiento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.movimiento (
    id character varying(255) NOT NULL,
    fecha timestamp without time zone,
    saldo numeric(19,2),
    tipo_movimiento character varying(255),
    valor numeric(19,2),
    cuenta_id character varying(255)
);


ALTER TABLE public.movimiento OWNER TO postgres;

--
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.cliente (id, direccion, edad, genero, identificacion, nombre, telefono, contrasena, estado) VALUES ('50535ee7-9c4a-42bb-907f-f3af073ee299', 'Otavalo sn y principal', 30, 'MASCULINO', '1753412871', 'Jose Lema', '098254785', '1234', true);


--
-- Data for Name: cuenta; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.cuenta (id, estado, numero_cuenta, saldo_inicial, tipo_cuenta, cliente_id) VALUES ('26788443-0bc0-42f2-9ae9-987ee1016ee3', true, '585545', 1000.00, 'CORRIENTE', '50535ee7-9c4a-42bb-907f-f3af073ee299');
INSERT INTO public.cuenta (id, estado, numero_cuenta, saldo_inicial, tipo_cuenta, cliente_id) VALUES ('62dd19b0-a807-4166-960f-615356c03cc1', true, '000478758', 2010.00, 'AHORRO', '50535ee7-9c4a-42bb-907f-f3af073ee299');


--
-- Data for Name: movimiento; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.movimiento (id, fecha, saldo, tipo_movimiento, valor, cuenta_id) VALUES ('9a7b590d-4e3d-4f15-8be2-6a6ade4eab52', '2022-05-01 14:00:06', 2000.00, 'DEBITO', 10.00, '62dd19b0-a807-4166-960f-615356c03cc1');
INSERT INTO public.movimiento (id, fecha, saldo, tipo_movimiento, valor, cuenta_id) VALUES ('d3c809b7-0c21-4ddc-a954-83bda231ca47', '2022-05-10 14:00:06', 1990.00, 'DEBITO', 10.00, '62dd19b0-a807-4166-960f-615356c03cc1');
INSERT INTO public.movimiento (id, fecha, saldo, tipo_movimiento, valor, cuenta_id) VALUES ('f8031ffc-1f0d-405a-ad81-8aa3fe5a7f66', '2022-05-15 14:00:06', 1980.00, 'DEBITO', 10.00, '62dd19b0-a807-4166-960f-615356c03cc1');
INSERT INTO public.movimiento (id, fecha, saldo, tipo_movimiento, valor, cuenta_id) VALUES ('8f3e4cd9-2969-4593-9166-ebd2131240a4', '2022-05-20 14:00:06', 1970.00, 'DEBITO', 10.00, '62dd19b0-a807-4166-960f-615356c03cc1');
INSERT INTO public.movimiento (id, fecha, saldo, tipo_movimiento, valor, cuenta_id) VALUES ('38e15113-2175-45d7-87bc-746bfd2c0c08', '2022-06-04 14:00:04', 2020.00, 'CREDITO', 50.00, '62dd19b0-a807-4166-960f-615356c03cc1');


--
-- Name: cliente cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);


--
-- Name: cuenta cuenta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT cuenta_pkey PRIMARY KEY (id);


--
-- Name: movimiento movimiento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimiento
    ADD CONSTRAINT movimiento_pkey PRIMARY KEY (id);


--
-- Name: movimiento fk4ea11fe7p3xa1kwwmdgi9f2fi; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimiento
    ADD CONSTRAINT fk4ea11fe7p3xa1kwwmdgi9f2fi FOREIGN KEY (cuenta_id) REFERENCES public.cuenta(id);


--
-- Name: cuenta fk4p224uogyy5hmxvn8fwa2jlug; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT fk4p224uogyy5hmxvn8fwa2jlug FOREIGN KEY (cliente_id) REFERENCES public.cliente(id);


--
-- PostgreSQL database dump complete
--

