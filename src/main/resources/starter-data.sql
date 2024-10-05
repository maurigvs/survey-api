--
-- Data for Name: survey; Type: TABLE DATA; Schema: public; Owner: pgdbuser
--

INSERT INTO public.survey VALUES (1, 'Sample Survey');


--
-- Data for Name: answer; Type: TABLE DATA; Schema: public; Owner: pgdbuser
--

INSERT INTO public.answer VALUES (1, 1);


--
-- Data for Name: question; Type: TABLE DATA; Schema: public; Owner: pgdbuser
--

INSERT INTO public.question VALUES (1, 'Where does Santa Claus live?', 1);
INSERT INTO public.question VALUES (2, 'Where you satisfied with your Christmas presents?', 1);
INSERT INTO public.question VALUES (3, 'Which city is the capital of Parana', 1);


--
-- Data for Name: choice; Type: TABLE DATA; Schema: public; Owner: pgdbuser
--

INSERT INTO public.choice VALUES (1, 'Hawaii', 1);
INSERT INTO public.choice VALUES (2, 'Finland', 1);
INSERT INTO public.choice VALUES (3, 'Sweden', 1);
INSERT INTO public.choice VALUES (4, 'Istambul', 1);
INSERT INTO public.choice VALUES (5, 'Very satisfied', 2);
INSERT INTO public.choice VALUES (6, 'Neither satisfied or dissatisfied', 2);
INSERT INTO public.choice VALUES (7, 'Dissatisfied', 2);
INSERT INTO public.choice VALUES (8, 'Very dissatisfied', 2);
INSERT INTO public.choice VALUES (9, 'Londrina', 3);
INSERT INTO public.choice VALUES (10, 'Maringa', 3);
INSERT INTO public.choice VALUES (11, 'Curitiba', 3);
INSERT INTO public.choice VALUES (12, 'Foz do Iguacu', 3);


--
-- Data for Name: answer_item; Type: TABLE DATA; Schema: public; Owner: pgdbuser
--

INSERT INTO public.answer_item VALUES (1, 1, 2, 1);
INSERT INTO public.answer_item VALUES (2, 1, 6, 2);
INSERT INTO public.answer_item VALUES (3, 1, 10, 3);


