CREATE TABLE IF NOT EXISTS public.account (
                                              accountid SERIAL PRIMARY KEY,
                                              customerid INTEGER,
                                              country VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS public.balance (
                                              balanceid SERIAL PRIMARY KEY,
                                              accountid INTEGER,
                                              currencyid INTEGER,
                                              amount NUMERIC(10,2),
                                              FOREIGN KEY (accountid) REFERENCES public.account(accountid),
                                              FOREIGN KEY (currencyid) REFERENCES public.currency(currencyid)
);

CREATE TABLE IF NOT EXISTS public.currency (
                                               currencyid SERIAL PRIMARY KEY,
                                               currencycode VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS public.transaction (
                                                  transactionid SERIAL PRIMARY KEY,
                                                  accountid INTEGER NOT NULL,
                                                  amount NUMERIC NOT NULL,
                                                  direction VARCHAR(3) NOT NULL,
                                                  description TEXT NOT NULL,
                                                  currency VARCHAR(3) NOT NULL,
                                                  balancebefore DOUBLE PRECISION,
                                                  FOREIGN KEY (accountid) REFERENCES public.account(accountid)
);

INSERT INTO public.currency (currencycode) VALUES
                                               ('USD'),
                                               ('EUR'),
                                               ('SEK'),
                                               ('GBP');
