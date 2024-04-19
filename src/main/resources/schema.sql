--
-- PostgreSQL database dump
--

-- Dumped from database version 14.11 (Homebrew)
-- Dumped by pg_dump version 14.11 (Homebrew)

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: account; Type: TABLE; Schema: public; Owner: tuum_app
--

CREATE TABLE public.account (
    accountid integer NOT NULL,
    customerid integer,
    country character varying(255) NOT NULL
);


ALTER TABLE public.account OWNER TO tuum_app;

--
-- Name: account_accountid_seq; Type: SEQUENCE; Schema: public; Owner: tuum_app
--

CREATE SEQUENCE public.account_accountid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.account_accountid_seq OWNER TO tuum_app;

--
-- Name: account_accountid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: tuum_app
--

ALTER SEQUENCE public.account_accountid_seq OWNED BY public.account.accountid;


--
-- Name: balance; Type: TABLE; Schema: public; Owner: tuum_app
--

CREATE TABLE public.balance (
    balanceid integer NOT NULL,
    accountid integer,
    currencyid integer,
    amount numeric(10,2)
);


ALTER TABLE public.balance OWNER TO tuum_app;

--
-- Name: balance_balanceid_seq; Type: SEQUENCE; Schema: public; Owner: tuum_app
--

CREATE SEQUENCE public.balance_balanceid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.balance_balanceid_seq OWNER TO tuum_app;

--
-- Name: balance_balanceid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: tuum_app
--

ALTER SEQUENCE public.balance_balanceid_seq OWNED BY public.balance.balanceid;


--
-- Name: currency; Type: TABLE; Schema: public; Owner: tuum_app
--

CREATE TABLE public.currency (
    currencyid integer NOT NULL,
    currencycode character varying(255)
);


ALTER TABLE public.currency OWNER TO tuum_app;

--
-- Name: currency_currencyid_seq; Type: SEQUENCE; Schema: public; Owner: tuum_app
--

CREATE SEQUENCE public.currency_currencyid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.currency_currencyid_seq OWNER TO tuum_app;

--
-- Name: currency_currencyid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: tuum_app
--

ALTER SEQUENCE public.currency_currencyid_seq OWNED BY public.currency.currencyid;


--
-- Name: transaction; Type: TABLE; Schema: public; Owner: tuum_app
--

CREATE TABLE public.transaction (
    transactionid integer NOT NULL,
    accountid integer NOT NULL,
    amount numeric NOT NULL,
    direction character varying(3) NOT NULL,
    description text NOT NULL,
    currency character varying(3) NOT NULL,
    balancebefore double precision
);


ALTER TABLE public.transaction OWNER TO tuum_app;

--
-- Name: transactions_transaction_id_seq; Type: SEQUENCE; Schema: public; Owner: tuum_app
--

CREATE SEQUENCE public.transactions_transaction_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transactions_transaction_id_seq OWNER TO tuum_app;

--
-- Name: transactions_transaction_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: tuum_app
--

ALTER SEQUENCE public.transactions_transaction_id_seq OWNED BY public.transaction.transactionid;


--
-- Name: account accountid; Type: DEFAULT; Schema: public; Owner: tuum_app
--

ALTER TABLE ONLY public.account ALTER COLUMN accountid SET DEFAULT nextval('public.account_accountid_seq'::regclass);


--
-- Name: balance balanceid; Type: DEFAULT; Schema: public; Owner: tuum_app
--

ALTER TABLE ONLY public.balance ALTER COLUMN balanceid SET DEFAULT nextval('public.balance_balanceid_seq'::regclass);


--
-- Name: currency currencyid; Type: DEFAULT; Schema: public; Owner: tuum_app
--

ALTER TABLE ONLY public.currency ALTER COLUMN currencyid SET DEFAULT nextval('public.currency_currencyid_seq'::regclass);


--
-- Name: transaction transactionid; Type: DEFAULT; Schema: public; Owner: tuum_app
--

ALTER TABLE ONLY public.transaction ALTER COLUMN transactionid SET DEFAULT nextval('public.transactions_transaction_id_seq'::regclass);


--
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: tuum_app
--

COPY public.account (accountid, customerid, country) FROM stdin;
\.


--
-- Data for Name: balance; Type: TABLE DATA; Schema: public; Owner: tuum_app
--

COPY public.balance (balanceid, accountid, currencyid, amount) FROM stdin;
\.


--
-- Data for Name: currency; Type: TABLE DATA; Schema: public; Owner: tuum_app
--

COPY public.currency (currencyid, currencycode) FROM stdin;
1	USD
2	EUR
3	GBP
4	SEK
\.


--
-- Data for Name: transaction; Type: TABLE DATA; Schema: public; Owner: tuum_app
--

COPY public.transaction (transactionid, accountid, amount, direction, description, currency, balancebefore) FROM stdin;
\.


--
-- Name: account_accountid_seq; Type: SEQUENCE SET; Schema: public; Owner: tuum_app
--

SELECT pg_catalog.setval('public.account_accountid_seq', 1794, true);


--
-- Name: balance_balanceid_seq; Type: SEQUENCE SET; Schema: public; Owner: tuum_app
--

SELECT pg_catalog.setval('public.balance_balanceid_seq', 3553, true);


--
-- Name: currency_currencyid_seq; Type: SEQUENCE SET; Schema: public; Owner: tuum_app
--

SELECT pg_catalog.setval('public.currency_currencyid_seq', 4, true);


--
-- Name: transactions_transaction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: tuum_app
--

SELECT pg_catalog.setval('public.transactions_transaction_id_seq', 31, true);


--
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: tuum_app
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (accountid);


--
-- Name: balance balance_pkey; Type: CONSTRAINT; Schema: public; Owner: tuum_app
--

ALTER TABLE ONLY public.balance
    ADD CONSTRAINT balance_pkey PRIMARY KEY (balanceid);


--
-- Name: currency currency_pkey; Type: CONSTRAINT; Schema: public; Owner: tuum_app
--

ALTER TABLE ONLY public.currency
    ADD CONSTRAINT currency_pkey PRIMARY KEY (currencyid);


--
-- Name: transaction transactions_pkey; Type: CONSTRAINT; Schema: public; Owner: tuum_app
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT transactions_pkey PRIMARY KEY (transactionid);


--
-- Name: balance balance_accountid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: tuum_app
--

ALTER TABLE ONLY public.balance
    ADD CONSTRAINT balance_accountid_fkey FOREIGN KEY (accountid) REFERENCES public.account(accountid);


--
-- Name: balance balance_currencyid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: tuum_app
--

ALTER TABLE ONLY public.balance
    ADD CONSTRAINT balance_currencyid_fkey FOREIGN KEY (currencyid) REFERENCES public.currency(currencyid);


--
-- PostgreSQL database dump complete
--

