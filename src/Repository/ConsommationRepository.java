package Repository;

import Entity.Alimentation;
import Entity.Consommation;
import Entity.Logement;
import Entity.Transport;
import Interfaces.RepositoryInterface;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ConsommationRepository implements RepositoryInterface<Consommation> {

    private final Connection connection;

    public ConsommationRepository(Connection connection) {
        this.connection = connection;
    }

    public Optional<Consommation> addConsommation(Consommation consommation) {
        String sql = null;
        if (consommation instanceof Alimentation) {
            sql = "INSERT INTO alimentations (value, startdate, enddate, types_consommation_id, user_id, type_aliment, poids) VALUES (?, ?, ?, ?, ?, ?, ?)";
        } else if (consommation instanceof Logement) {
            sql = "INSERT INTO logement (value, startdate, enddate, types_consommation_id, user_id, type_energie, consommation_energie) VALUES (?, ?, ?, ?, ?, ?, ?)";
        } else if (consommation instanceof Transport) {
            sql = "INSERT INTO transport (value, startdate, enddate, types_consommation_id, user_id, distance, type_transport) VALUES (?, ?, ?, ?, ?, ?, ?)";
        }

        if (sql != null) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setDouble(1, consommation.getValue());
                ps.setDate(2, Date.valueOf(consommation.getStartDate()));
                ps.setDate(3, Date.valueOf(consommation.getEndDate()));
                ps.setInt(4, consommation.getTypeConsommation());
                ps.setInt(5, consommation.getUser().getId());

                // Insertion des champs spécifiques à chaque type de consommation
                if (consommation instanceof Alimentation) {
                    Alimentation alimentation = (Alimentation) consommation;
                    ps.setString(6, alimentation.getTypeAliment().toString());
                    ps.setDouble(7, alimentation.getPoids());
                } else if (consommation instanceof Logement) {
                    Logement logement = (Logement) consommation;
                    ps.setString(6, logement.getTypeEnergie().toString());
                    ps.setDouble(7, logement.getConsommationEnergie());
                } else if (consommation instanceof Transport) {
                    Transport transport = (Transport) consommation;
                    ps.setDouble(6, transport.getDistance());
                    ps.setString(7, transport.getTypeVehicule().toString());
                }

                ps.executeUpdate();
                return Optional.of(consommation);
            } catch (SQLException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

//    public void insertConsummation(Consommation consommation) {
//        String sql = null;
//
//        if (consommation instanceof Alimentation) {
//            sql = "INSERT INTO alimentation (user_id, quantity, start_date, end_date,type_id, type_aliment, poids ) VALUES (?, ?, ?, ?, ?, ?, ?)";
//        } else if (consommation instanceof Transport) {
//            sql = "INSERT INTO transport (user_id, quantity, start_date, end_date,type_id, vehicule, distance ) VALUES (?, ?, ?, ?, ?, ?, ?)";
//        } else if (consommation instanceof Logement) {
//            sql = "INSERT INTO logement (user_id, quantity, start_date, end_date,type_id, type_energie, consommation_energie) VALUES (?, ?, ?, ?, ?, ?, ?)";
//        }
//
//        if (sql != null) {
//            try (PreparedStatement ps = connection.connectToDB().prepareStatement(sql)) {
//                ps.setInt(1, consommation.getUser_id());
//                ps.setDouble(2, consommation.getQuantity());
//                ps.setDate(3, Date.valueOf(consommation.getDateDebut()));
//                ps.setDate(4, Date.valueOf(consommation.getDateFin()));
//                ps.setInt(5, consommation.getType_id());
//
//                if (consommation instanceof Alimentation) {
//                    Alimentation alimentation = (Alimentation) consommation;
//                    ps.setString(6, String.valueOf(alimentation.getType_aliment()));
//                    ps.setDouble(7, alimentation.getPoids());
//                } else if (consommation instanceof Transport) {
//                    Transport transport = (Transport) consommation;
//                    ps.setString(6, String.valueOf(transport.getType()));
//                    ps.setDouble(7, transport.getDistance_parcourure());
//                } else {
//                    Logement logement = (Logement) consommation;
//                    ps.setString(6, String.valueOf(logement.getType()));
//                    ps.setDouble(7, logement.getConsommation_energie());
//                }
//
//                ps.executeUpdate();
//            } catch (SQLException e) {
//                System.out.println(e);
//            }
//        }
//    }

    @Override
    public Optional<Consommation> create(Consommation entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Consommation> update(Consommation entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Consommation> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Consommation> getAll() {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
