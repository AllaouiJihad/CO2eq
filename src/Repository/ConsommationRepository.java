package Repository;

import Entity.Alimentation;
import Entity.Consommation;
import Entity.Logement;
import Entity.Transport;
import Interfaces.RepositoryInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConsommationRepository {

    private final Connection connection;

    public ConsommationRepository(Connection connection) {
        this.connection = connection;
    }

    public Optional<Consommation> addConsommation(Consommation consommation) {
        String sql = null;
        if (consommation instanceof Alimentation) {
            sql = "INSERT INTO alimentation (value, startdate, enddate, types_consommation_id, user_id, type_aliment, poids) VALUES (?, ?, ?, ?, ?, ?, ?)";
        } else if (consommation instanceof Logement) {
            sql = "INSERT INTO logement (value, startdate, enddate, types_consommation_id, user_id, type_energie, consommation_energie) VALUES (?, ?, ?, ?, ?, ?, ?)";
        } else if (consommation instanceof Transport) {
            sql = "INSERT INTO tronsport (value, startdate, enddate, types_consommation_id, user_id, distance, type_transport) VALUES (?, ?, ?, ?, ?, ?, ?)";
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

    public List<Consommation> getConsommationsByUserId(int userId) {
        List<Consommation> consommations = new ArrayList<>();
        String sql = "SELECT * FROM consommations WHERE user_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Consommation consommation = createConsommationFromResultSet(rs);
                    if (consommation != null) {
                        consommations.add(consommation);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception comme approprié pour votre application
        }

        return consommations;
    }
}
