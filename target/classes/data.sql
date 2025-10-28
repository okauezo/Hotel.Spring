-- HÓSPEDES
INSERT INTO guests (id, full_name, document, email, phone, created_at) VALUES
                                                                           ('11111111-1111-1111-1111-111111111111','Ana Silva','12345678901','ana@example.com','+55-11-99999-1111', CURRENT_TIMESTAMP),
                                                                           ('22222222-2222-2222-2222-222222222222','Bruno Souza','98765432100','bruno@example.com','+55-21-98888-2222', CURRENT_TIMESTAMP);

-- QUARTOS
INSERT INTO rooms (id, number, type, capacity, price_per_night, status) VALUES
                                                                            ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',101,'STANDARD',2,250.00,'ATIVO'),
                                                                            ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb',201,'DELUXE',3,380.00,'ATIVO'),
                                                                            ('cccccccc-cccc-cccc-cccc-cccccccccccc',301,'SUITE',4,520.00,'ATIVO');

-- RESERVA EXEMPLO (sem sobreposição)
INSERT INTO reservations (id, guest_id, room_id, checkin_expected, checkout_expected, status, estimated_amount, created_at)
VALUES ('99999999-9999-9999-9999-999999999999',
        '11111111-1111-1111-1111-111111111111',
        'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
        DATE '2025-11-05', DATE '2025-11-07', 'CREATED', 2 * 250.00, CURRENT_TIMESTAMP);
