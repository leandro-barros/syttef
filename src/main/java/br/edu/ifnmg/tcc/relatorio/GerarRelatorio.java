package br.edu.ifnmg.tcc.relatorio;

import br.edu.ifnmg.tcc.entidade.Animal;
import br.edu.ifnmg.tcc.util.DataUtil;
import br.edu.ifnmg.tcc.util.HibernateUtil;
import br.edu.ifnmg.tcc.util.ReportUtil;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import org.hibernate.engine.spi.SessionImplementor;

public class GerarRelatorio {

  public byte[] gerarRelatorioEntregaLeite(Date dataInicio, Date dataFim) throws Exception {
    InputStream rel = getClass().getResourceAsStream("/br/edu/ifnmg/tcc/relatorio/relatorio_entregar_leite.jrxml");
    SessionImplementor sim = (SessionImplementor) HibernateUtil.getSession();
    Connection c = sim.connection();
    HashMap<String, Object> valores = new HashMap<>();
    valores.put("REPORT_CONNECTION", c);
    dataInicio = DataUtil.truncate(dataInicio);
    dataFim = DataUtil.truncateEndDay(dataFim);
    Timestamp timeInicio = new Timestamp(dataInicio.getTime());
    Timestamp timeFim = new Timestamp(dataFim.getTime());
    valores.put("DATA_INICIO", timeInicio);
    valores.put("DATA_FIM", timeFim);
    byte[] pdf = ReportUtil.reportToPDF(null, rel, valores);
    return pdf;
  }
  
  public byte[] gerarRelatorioAlimentacao(Date dataInicio, Date dataFim, Animal animal) throws Exception {
    InputStream rel = getClass().getResourceAsStream("/br/edu/ifnmg/tcc/relatorio/relatorio_alimentacao.jrxml");
    SessionImplementor sim = (SessionImplementor) HibernateUtil.getSession();
    Connection c = sim.connection();
    HashMap<String, Object> valores = new HashMap<>();
    valores.put("REPORT_CONNECTION", c);
    dataInicio = DataUtil.truncate(dataInicio);
    dataFim = DataUtil.truncateEndDay(dataFim);
    Timestamp timeInicio = new Timestamp(dataInicio.getTime());
    Timestamp timeFim = new Timestamp(dataFim.getTime());
    valores.put("DATA_INICIO", timeInicio);
    valores.put("DATA_FIM", timeFim);
    valores.put("ANIMAL_ID", animal.getId());
    byte[] pdf = ReportUtil.reportToPDF(null, rel, valores);
    return pdf;
  }
  public byte[] gerarRelatorioProducaoLeite(Date dataInicio, Date dataFim, Animal animal) throws Exception {
    InputStream rel = getClass().getResourceAsStream("/br/edu/ifnmg/tcc/relatorio/relatorio_producao_leite.jrxml");
    SessionImplementor sim = (SessionImplementor) HibernateUtil.getSession();
    Connection c = sim.connection();
    HashMap<String, Object> valores = new HashMap<>();
    valores.put("REPORT_CONNECTION", c);
    dataInicio = DataUtil.truncate(dataInicio);
    dataFim = DataUtil.truncateEndDay(dataFim);
    Timestamp timeInicio = new Timestamp(dataInicio.getTime());
    Timestamp timeFim = new Timestamp(dataFim.getTime());
    valores.put("DATA_INICIO", timeInicio);
    valores.put("DATA_FIM", timeFim);
    valores.put("ANIMAL_ID", animal.getId());
    byte[] pdf = ReportUtil.reportToPDF(null, rel, valores);
    return pdf;
  }

}
